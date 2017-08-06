<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
           
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
     <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
     <!-- <link rel='stylesheet' href="<c:url value='/resources/bootstrap-3.3.7-dist/css/bootstrap.min.css'/>"> -->
     <title>One Page Resume</title>


    <link rel='stylesheet' href="<c:url value='/resources/style.css'/>">

     <style type="text/css">
        * { margin: 0; padding: 0; }
        body { font: 16px Helvetica, Sans-Serif; line-height: 24px; background: url(images/noise.jpg); }
        .clear { clear: both; }
        #page-wrap { width: 800px; margin: 40px auto 60px; }
        #pic { float: right; margin: -30px 0 0 0; }
        h1 { margin: 0 0 16px 0; padding: 0 0 16px 0; font-size: 42px; font-weight: bold; letter-spacing: -2px; border-bottom: 1px solid #999; }
        h2 { font-size: 20px; margin: 0 0 6px 0; position: relative; }
        h2 span { position: absolute; bottom: 0; right: 0; font-style: italic; font-family: Georgia, Serif; font-size: 16px; color: #999; font-weight: normal; }
        p { margin: 0 0 16px 0; }
        a { color: #999; text-decoration: none; border-bottom: 1px dotted #999; }
        a:hover { border-bottom-style: solid; color: black; }
        ul { margin: 0 0 32px 17px; }
        #objective { width: 500px; float: left; }
        #objective p { font-family: Georgia, Serif; font-style: italic; color: #666; }
        dt { font-style: italic; font-weight: bold; font-size: 18px; text-align: right; padding: 0 26px 0 0; width: 150px; float: left; height: 100px; border-right: 1px solid #999;  }
        dd { width: 600px; float: right; }
        dd.clear { float: none; margin: 0; height: 15px; }
     </style>

</head>

<body>
	<div><%@include file="navbar.jsp" %></div>
    <div id="page-wrap">
    
        <img src="<c:url value='/resources/old.jpeg'/>" alt="Photo of Youthful William" id="pic" />
    
        <div id="contact-info" class="vcard">
        
            <!-- Microformats! -->
        
            <h1 class="fn">William Rigby</h1>
        
            <p>
                Cell: <span class="tel">270-872-8109</span><br/>
                Email: <a class="email" href="mailto:williamlouisrigby@gmail.com">williamlouisrigby@gmail.com</a>
            </p>
        </div>
                
        <div id="objective">
            <p>
               I'm a senior majoring in Computer Engineering & Computer Science (CECS) at University of Louisville.
			   I have over 2 years of work experience on a corporate development team, using a large variety of technologies.
			   I'm looking for work and am open to both front end and back end development, but would like to work on products
			   which have a well defined group of end users.  THIS IS A TEST FOR THE MULTIPLE REPOS!!!!
            </p>
        </div>
        
        <div class="clear"></div>
        
        <dl>
            <dd class="clear"></dd>
            
            <dt>Education</dt>
            <dd>
                <h2>University of Louisville</h2>
                <p><strong>Major:</strong> Computer Engineering & Computer Science<br />
            </dd>
			<dd>
                <h2>Bellarmine University</h2>
                <p><strong>Major:</strong> Business Administration<br />
                   <strong>Minor:</strong> Political Science</p>
            </dd>

            <dd class="clear"></dd>

            <dt>Work Experience</dt>
            <dd>
                <h2>University of Louisville, CECS Department</h2>
                <h4>Mobile Application Developer, Louisville,KY, March 2017-Present</h4>
                <ul>
                    <li>Help to create two mobile applications using Xamarin Forms for Kindred Healthcare</li>
                    <li>Interface with Kindred team members, using Scrum style development</li>
                    <li>Assist in the creation of new design patterns</li>
                </ul>
                <h2>United Parcel Service</h2>
                <h4>Application Development Co-op, Louisville,KY, May 2015-August 2015 & January 2016-Decemeber 2016</h4>
                <ul>
                    <li>Assisted with software development in a middleware departmen</li>
                    <li>Assisted in the prototyping of an integration testing tool for the department’s main application. Built using an ASP .NET MVC frontend, with Java/Camel backend web services, which accessed a T-SQL database</li>
                    <li>Modified existing message routes, using the Java integration framework Apache Camel, the Spring framework, and implemented JUnit tests for added code</li>
                </ul>
            </dd>
            <dd class="clear"></dd>
            
            <dt>Skills</dt>
            <dd>                            
                <h2>Technologies with Advanced Knowledge</h2>
                <p>Java, C#, C</p>
				<h2>Technologies with Intermediate Knowledge</h2>
                <p>SQL Server, Javascript, HTML/CSS</p>
				<h2>Technologies with Working Knowledge</h2>
                <p>MySQL, Python, C++</p>
            </dd>
            

            
            <dd class="clear"></dd>
            
            <dt>Hobbies</dt>
            <dd>Beer brewing, concert going</dd>
            
            <dd class="clear"></dd>
            
            <dt>References</dt>
            <dd>Available on request</dd>
            
            <dd class="clear"></dd>
        </dl>
        
        <div class="clear"></div>

    </div>
	
	<!-- <script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script> -->
	<!-- <script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script> -->
</body>
</html>