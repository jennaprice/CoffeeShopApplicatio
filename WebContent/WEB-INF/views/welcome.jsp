<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Grand Circus Coffee Shop: Welcome</title>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
	 <link href="resources/style.css" rel="stylesheet" />
  </head>
  <body>
    
    <section >
    
    
      <div class="container" id="header">



        <img src="resources/GrandCircusLogo.jpg" />
        <h2> Welcome ${customername} to the Grand Circus Coffee Shop</h2>
        <nav style="display:flex; content:flex-end; background-color: light blue;"> 
    		<a href="checkout"> My Account</a>
   	 	<a href="checkout"> Checkout</a>
    		<a href="checkout"> Log Out</a>
    		</nav>
       </div>
       <div class="container" id="main">

    	    <h4> We are glad to have you.</h4> 
    	    <a href="welcome" > Click here to view our products</a>
    	    ${update}
    	    
    	    <c:if test="${ table !=null}">  
    	     <table border="1">
			<c:forEach var="myVar" items="${table}"> <!-- message should be an array list of objects -->
			<tr>
				<td>${myVar.name}</td>
				<td>${myVar.description}</td>
				<td>${myVar.qty}</td>
				<td>${myVar.price}</td>
				<td><a href="purchase?id=${myVar.name}"> Purchase </a></td>
				

			</tr>

		</c:forEach>

	</table>
    	</c:if>
    	   
    	   
      </div>

    </section>


  </body>
</html>