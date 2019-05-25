<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Search Restaurant</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
     <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
     <div class="panel panel-default">
     			<div class="panel-heading"style="text-align: center;background-color: lightsteelblue;color: maroon;font-size: initial;">Welcome To KD Restaurant
     			<a onclick="document.forms['logoutForm'].submit()" style="float:right;color: red">Logout</a></div>
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <form id="logoutForm" method="POST" action="${contextPath}/logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </c:if>
     </div>
  <div class="container searchContainer">
     		<div class="panel panel-default">
     			<div class="panel-heading">View/Search Restaurant</div>
     			<div class="panel-body">
                    <div id="view-org-msg"></div>
                    <div id="view-org-form">
                        <form class="form-inline ajax" action="restaurant/search"
                                            method="get" data-error="#view-org-msg"
                                            data-spinner="#form-spinner" data-replace="#restaurant-search-results">
                                <div class="panel-body" style="padding-top: 0">
                                    <div class="row">
                                        <div class="form-group col-md-3">
                                            <div class="inner-addon left-addon">
                                                <label for="searchId" class="control-label" style="font-size: xx-small;">Searching for Restaurant</label>
                                                <input type="text" class="form-control searchInp" id="searchId"
                                                    name="search_restaurant" placeholder="Search restaurant,cuisines...">

                                                <i class="fa fa-search searchIcon" aria-hidden="true"></i>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                                <div id="form-spinner"></div>
                        </form>
                    </div>
     			</div>
     		</div>
     	<div id="restaurant-search-results" tabindex="1"></div>
  </div>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
      <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
  <script>
  //console.log("${contextPath}");
  $('input#searchId').keyup( function() {

     if( this.value.length < 3 ) return;
        var queryStr=this.value;
        var search = {"q":queryStr};
       var prefix = "${contextPath}";
       console.log("------------->>>>>>>>>",prefix);
       var link="restaurant/search";
       var url = prefix.concat(link);
        $.ajax({
          url:url,
          type: "post",

          contentType: "application/json",
          data: JSON.stringify(search),
           datatype : "json",
          success: function(response) {
          $('#restaurant-search-results').empty();
            $('#restaurant-search-results').html(response);
          },
          error: function(xhr) {
            console.log(xhr);
          }
        });

  });
  </script>

</body>
</html>
