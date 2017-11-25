<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Grand Circus Coffee Shop</title>
	<style>
	* {	
  		font-family: Ubuntu, sans-serif;
	}
    	section{
      display: flex;
      justify-content: center;
    }
		.container {
        display: flex;
        flex-direction: column;
        align-items: center;
        border: 2px gray solid;
        border-radius: 5px;
        padding: 10px;
        width: 50%;

		}
		a {
  			text-decoration: none;
		}

		a:hover {
  			text-decoration: underline;
		}
		p {
			text-align: center;

		}

	</style>
  </head>
  <body>
    <section >
      <div class="container">
        <h2> Welcome to the Grand Circus Coffee Shop</h2>
        <p>
          Please login.
        </p>
    	<form action="submitform" method="post" style="display:flex; flex-direction:column" >
        <label for="username">Username</label>
	       <input type="text" id="username">
         <label for="password">Password </label>
         <input type="text" id="password">
         <input type="submit" value="Log In" />
         <p>
            Don't have an account? <a href="register.html">Click Here</a> to register.
         </p>
       </form>
      </div>

    </section>


  </body>
</html>
