<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: anthonylabbe
  Date: 05/12/2017
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="./css/w3.css">
<link rel="stylesheet" href="./css/style.css">
<head>
    <title><s:text name="index.title"/></title>
</head>
<body class="w3-teal">

<h1 class="w3-center"><s:text name="index.bienvenue"/></h1>

</br>
</br>

<div class="w3-row">
    <div class="w3-col s2">
        <p></p>
    </div>
    <div class="w3-col s8">
        <h3 class="w3-center"><s:text name="index.connexion"/></h3>
        <s:form action="connexion">
            <s:textfield name="pseudo" key="index.pseudo" class="w3-input"/>
            <s:password name="mdp" key="index.mdp" class="w3-input"/>
            <s:submit key="index.form.connexion" class="w3-btn w3-center w3-blue-grey"/>
        </s:form>
    </div>
    <div class="w3-col s2">
        <p></p>
    </div>
</div>

<div class="w3-row">
    <div class="w3-col s2">
        <p></p>
    </div>
    <div class="w3-col s8">
        <h3 class="w3-center"><s:text name="index.inscription"/></h3>
        <s:form action="inscription">
            <s:textfield name="pseudoInscription" key="inscription.pseudo" class="w3-input"/>
            <s:password name="mdpInscription" key="inscription.mdp" class="w3-input"/>
            <s:submit key="index.form.inscription" class="w3-btn w3-center w3-blue-grey"/>
        </s:form>
    </div>
    <div class="w3-col s2">
        <p></p>
    </div>
</div>

</div>

<div class="w3-display-bottommiddle">
    <p><s:text name="lesDevs"/></p>
</div>
</body>
</html>