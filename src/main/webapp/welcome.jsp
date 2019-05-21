<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create an account</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
     <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
     <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css">
</head>
<body>
     <div class="panel panel-default">
     			<div class="panel-heading"style="text-align: center;background-color: lightsteelblue;color: maroon;font-size: initial;">Welcome To KD Restaurant
     			<a onclick="document.forms['logoutForm'].submit()" style="float:right;color: red">Logout</a></div>

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
          		</div>
  <div class="container">
     		<div class="panel panel-default">
     			<div class="panel-heading">View/Search Restaurant</div>
     			<div class="panel-body">
     						<div class="panel-body" style="padding-top: 0">
     							<div class="row">
     								<div class="form-group col-lg-2">
     									<div class="inner-addon left-addon">
     										<label for="searchId" class="control-label" style="font-size: xx-small;">Searching for Restaurant</label>
     										 <i class="glyphicon glyphicon-search"></i>
     										<input type="text" class="form-control" id="searchId"
     											name="search_restaurant" placeholder="Search restaurant,cuisines...">
     									</div>
     								</div>

     							</div>
     						</div>
     			</div>
     		</div>

    </c:if>
  </div>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
      <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
  <script>
  $('input#searchId').keyup( function() {
     if( this.value.length < 3 ) return;
        var queryStr=this.value;
       // var entity_type="city";
        $.ajax({
          url: "https://developers.zomato.com/api/v2.1/search",
          type: "get",
          data: {
            entity_type: "city",
            q:queryStr
          },
          beforeSend: function(xhr){
                                     xhr.setRequestHeader("user-key", "96b91246acf06fe50ccd86fe5d4514a1");
                                     xhr.setRequestHeader("Accept", "application/json");
                            },
          success: function(response) {
           console.log(response);
          },
          error: function(xhr) {
            console.log(xhr);
          }
        });

  });
  </script>

</body>
</html>
