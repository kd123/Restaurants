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
  <div class="restaurant-rating-results">
     		<div class="panel panel-default">
     			<div class="panel-heading">Restuarant Details</div>
     			<div class="panel-body">
     			<c:choose>
                                   	<c:when test="${not empty restaurant}">
                					<table style="table-layout: fixed"
                                    							class="table table-hover table-bordered">
                                    							<tr >
                                    								<th class="text-center">Restaurant Details</th>
                                    							</tr>
                                                                    <tr>
                                                                        <td>
                                                                            <div class="form-group col-md-3">
                                                                                <img class= "picture" src="${restaurant.thumb}" width="350px" height="150">
                                                                            </div>
                                                                            <div class="form-group col-md-4 res_details" style="margin-top: 15px;">
                                                                            <input type="hidden" id="rating_id" value="${restaurant.user_rating.id}"/>
                                                                            <input type="hidden" id="rating_resId" value="${restaurant.user_rating.resId}"/>

                                                                                <div class= "name">${restaurant.name}</div>
                                                                                <div class= "rating"
                                                                                style="background-color:${restaurant.user_rating.rating_color};float:right;"
                                                                                data-toggle="popover" data-trigger="hover" data-content="${restaurant.user_rating.rating_text}">${restaurant.user_rating.aggregate_rating}</div>

                                                                                <div class= "cuisines">Cusines: ${restaurant.cuisines}</div>

                                                                                <div class= "votes">Votes: ${restaurant.user_rating.votes}</div>
                                                                                <div class= "votes">Rs. ${restaurant.average_cost_for_two} for two people (approx.) </div>
                                                                                <div class= "url">Address: ${restaurant.locality.locality}, ${restaurant.locality.city} </div>

                                                                            </div>
                                                                            <div class="form-group col-md-3 otherInfo">
                                                                            </div>

                                                                        </td>

                                                                    </tr>

                                    						</table>

                					</c:when>
                					<c:otherwise>
                						No more data found.
                					</c:otherwise>
                				</c:choose>
                	<div class="user_rating">
                	 <label class = "full" style="margin-top: 6px;float: left;">Please Rate:</label>
                   <fieldset class="restaurant_rating">
                       <input type="radio" id="star5" name="rating" value="5" /><label class = "full" for="star5" title="Awesome - 5 stars"></label>
                       <input type="radio" id="star4half" name="rating" value="4.5" /><label class="half" for="star4half" title="Pretty good - 4.5 stars"></label>
                       <input type="radio" id="star4" name="rating" value="4" /><label class = "full" for="star4" title="Pretty good - 4 stars"></label>
                       <input type="radio" id="star3half" name="rating" value="3.5" /><label class="half" for="star3half" title="Meh - 3.5 stars"></label>
                       <input type="radio" id="star3" name="rating" value="3" /><label class = "full" for="star3" title="Meh - 3 stars"></label>
                       <input type="radio" id="star2half" name="rating" value="2.5" /><label class="half" for="star2half" title="Kinda bad - 2.5 stars"></label>
                       <input type="radio" id="star2" name="rating" value="2" /><label class = "full" for="star2" title="Kinda bad - 2 stars"></label>
                       <input type="radio" id="star1half" name="rating" value="1.5" /><label class="half" for="star1half" title="Meh - 1.5 stars"></label>
                       <input type="radio" id="star1" name="rating" value="1" /><label class = "full" for="star1" title="Sucks big time - 1 star"></label>
                       <input type="radio" id="starhalf" name="rating" value="0.5" /><label class="half" for="starhalf" title="Sucks big time - 0.5 stars"></label>
                   </fieldset>
                   </div>
                   <div><input type="button" value="Rate Now"></div>
     			</div>
     		</div>
  </div>
  </div>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
      <script src="${contextPath}/resources/js/bootstrap.min.js"></script>

      <script>
      $("input[type='button']").click(function(){
            var radioValue = $("input[name='rating']:checked").val();
            var id=$('#rating_id').val();
            var resId=$('#rating_resId').val();
            console.log("Radio---->"+radioValue);
            console.log("---->"+id);
            console.log(">>>>"+resId);
            var prefix = "${contextPath}";
            console.log("------------->>>>>>>>>"+prefix);
            var link="/restaurant/rating";
            var url = prefix.concat(link);
             console.log("------------->>>>>>>>>"+url);
            var search = {"id":id,
                           "res_id":resId,
                           "rating":radioValue};
             $.ajax({
                      url:url,
                      type: "post",

                      contentType: "application/json",
                      data: JSON.stringify(search),
                       datatype : "json",
                      success: function(response) {
                      $('#restaurant-rating-results').empty();
                        $('#restaurant-rating-results').html(response);
                      },
                      error: function(xhr) {
                        console.log(xhr);
                      }
                    });
        });
      </script>
   </body>
   </html>
