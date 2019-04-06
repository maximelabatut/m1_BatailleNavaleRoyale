<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: anthonylabbe
  Date: 05/12/2017
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="./css/w3.css">
<head>
    <title><s:text name="creationPartie.title"/><s:property value="pseudo"/></title>
</head>
<body class="w3-teal">

<h1 class="w3-center"><s:text name="creationPartie.text"/></h1>

<div class="w3-row">
    <div class="w3-col s1">
        <p></p>
    </div>
    <div class="w3-col s10">
        <h2><s:text name="creationPartie.instructions"/></h2>
        <s:form action="CreerPartie">
            <s:radio list="nbJoueurs" key="creationPartie.form.nbJoueurs" name="nbJoueurs" checked="true"/>
            <s:submit key="creationPartie.creerPartie" class="w3-btn w3-center w3-blue-grey w3-margin-top"/>
        </s:form>


        <s:form action="retourMenu">
            <s:submit key="creationPartie.retour" class="w3-btn w3-center w3-blue-grey"/>
        </s:form>
    </div>
    <div class="w3-col s1">
        <p></p>
    </div>
</div>
</body>
</html>
