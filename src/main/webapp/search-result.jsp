<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
  <div class="restaurant-search-results">
     		<div class="panel panel-default">
     			<div class="panel-heading">Result</div>
     			<div class="panel-body">
                   <c:choose>
                   	<c:when test="${not empty restaurants}">
					<table style="table-layout: fixed"
                    							class="table table-hover table-bordered">
                    							<tr >
                    								<th colspan="2" class="text-center">Restaurant Info</th>
                    							</tr>
                    							<c:forEach items="${restaurants}" var="restaurant" varStatus="loop">
                    							<c:set value="${restaurant}" var="restaurantvalues"></c:set>
                                                    <c:if test="${loop.count % 2 == 1}">
                                                    <tr>
                                                    </c:if>

                                                        <td>
                                                        <a href="${contextPath}/restaurant/restaurant-details/${restaurant.id}" target="_blank">
                                                            <div class="form-group col-md-4">
                                                                <img class= "picture" src="${restaurant.thumb}" width="250px" height="150">
                                                            </div>
                                                            <div class="form-group col-md-5 res_details">
                                                                <div class= "name">${restaurant.name}</div>
                                                                <div class= "rating"
                                                                style="background-color:#${restaurant.user_rating.rating_color};float:right;"
                                                                data-toggle="popover" data-trigger="hover" data-content="${restaurant.user_rating.rating_text}">${restaurant.user_rating.aggregate_rating}</div>

                                                                <div class= "cuisines">Cusines: ${restaurant.cuisines}</div>

                                                                <div class= "votes">Votes: ${restaurant.user_rating.votes}</div>
                                                                <div class= "votes">Rs. ${restaurant.average_cost_for_two} for two people (approx.) </div>
                                                                <div class= "url"><a href="${restaurant.url}" target="_blank">Open with Zomato</a></div>

                                                            </div>
                                                            <div class="form-group col-md-3 otherInfo">
                                                            </div>
                                                             </a>
                                                        </td>

                                                        <c:if test="${loop.count % 2 == 0||loop.count == fn:length(values)}">
                                                    </tr>
                                                    </c:if>
                    							</c:forEach>
                    						</table>

					</c:when>
					<c:otherwise>
						No more data found.
					</c:otherwise>
				</c:choose>
     			</div>
     		</div>
  </div>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
      <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
  <script>
         $(document).ready(function() {
              $('[data-toggle="popover"]').popover();
         });
   </script>