<%--suppress JSUnresolvedLibraryURL --%>
<!DOCTYPE html>
<html>
<head>

  <%
    String _contextPath = request.getContextPath();
  %>
  <script>
    var _contextPath = '<%=_contextPath%>';
  </script>

  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

  <title>AgTrack</title>

  <!-- FOR ANGULAR ROUTING -->
  <base href="/">

  <!-- CSS  -->
  <!-- load bootstrap from CDN and custom CSS -->
  <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.1.1/animate.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/nvd3/1.8.1/nv.d3.min.css"/>
  <link rel="stylesheet" href="https://raw.githack.com/ManifestWebDesign/angular-gridster/v0.13.5/dist/angular-gridster.min.css"/>
  <link rel="stylesheet" href="<%=_contextPath%>/assets/css/style.css"/>


  <!-- JS -->
  <!-- load angular and angular-route via CDN -->
  <!-- Auth0's lock widget library -->
  <script type="text/javascript" src="https://cdn.auth0.com/js/lock/10.0.0-rc.1/lock.min.js"></script>
  <!-- Auth0 JavaScript library -->
  <script type="text/javascript" src="https://cdn.auth0.com/w2/auth0-7.0.3.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.9/angular.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.8/angular-route.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-touch/1.5.7/angular-touch.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.8/angular-animate.js"></script>
  <!-- Angular cookie wrapper library for client cookies -->
  <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-cookies.min.js"></script>
  <!-- Auth0's Angular SDK Library -->
  <script type="text/javascript" src="https://cdn.auth0.com/w2/auth0-angular/5.0.0.rc.1/auth0-angular.min.js"></script>
  <!-- Angular wrapper for localStorage and sessionStorage. Defaults to ng-cookies if not available -->
  <script src="http://cdn.rawgit.com/auth0/angular-storage/master/dist/angular-storage.min.js" type="text/javascript"> </script>
  <!-- Angular wrapper library for JWT-->
  <script src="http://cdn.rawgit.com/auth0/angular-jwt/master/dist/angular-jwt.min.js" type="text/javascript"> </script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/1.3.3/ui-bootstrap-tpls.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.6/d3.min.js" charset="utf-8"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/nvd3/1.8.1/nv.d3.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-nvd3/1.0.5/angular-nvd3.min.js"></script>
  <script src="https://raw.githack.com/ManifestWebDesign/angular-gridster/v0.13.5/dist/angular-gridster.min.js"></script>


  <!-- main Angular app files -->
  <script src="<%=_contextPath%>/app/app.js"></script>
  <script src="<%=_contextPath%>/app/app.routes.js"></script>
  <script src="<%=_contextPath%>/app/main/main.controller.js"></script>
  <script src="<%=_contextPath%>/app/login/login.controller.js"></script>


<%--Grower--%>
  <script src="<%=_contextPath%>/app/grower/grower.controller.js"></script>
  <script src="<%=_contextPath%>/app/grower/grower.service.js"></script>

  <%--Crops--%>
  <script src="<%=_contextPath%>/app/crop/crop.controller.js"></script>
  <script src="<%=_contextPath%>/app/crop/crop.service.js"></script>


</head>
<body ng-app="app" ng-controller="MainController as main">

<!-- HEADER AND NAVBAR -->
<header>
  <nav class="navbar navbar-default">
    <div class="navbar-header">
      <a class="navbar-brand" href="<%=_contextPath%>">
        <span style="margin-left:1.5em;">AgTrack</span>
      </a>
    </div>

    <ul class="nav navbar-nav navbar-right">
      <li><a href="">Hello Test Grower!</a></li>
      <li><a href="<%=_contextPath%>"><i class="fa fa-fort-awesome"></i> Home</a></li>
      <li><a href="<%=_contextPath%>/#/crops"><i class="fa fa-leaf"></i> Crops</a></li>
      <li><a href="" ng-if="main.loggedIn" ng-click="main.doLogout()"><i class="fa fa-sign-out"></i> Logout</a>
      </li>
    </ul>
  </nav>
</header>

<message></message>


<main class="container" ng-view></main>

</body>
</html>
