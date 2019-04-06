<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: anthonylabbe
  Date: 15/12/2017
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="./css/w3.css">
<head>
    <title><s:text name="salon.title"/><s:property value="idPartie"/><s:text name="salon.title.suite"/> <s:property
            value="pseudo"/></title>
    <META HTTP-EQUIV="Refresh" CONTENT="2;URL=CreerPartie.action">
</head>
<body class="w3-teal">

<h1 class="w3-center"><s:text name="partieTerminee.title"/></h1>

<div class="w3-row">
    <div class="w3-col s1">
        <p></p>
    </div>
    <div class="w3-col s10">
            <h2><s:text name="salon.joueursConnectes"/></h2>
        <ul>
            <s:iterator value="listeJoueursConnectes" var="joueur" status="status">
                <li><s:property/></li>
            </s:iterator>
        </ul>
    </div>
    <div class="w3-col s1">
        <p></p>
    </div>
</div>

<div class="w3-row">
    <div class="w3-col s1">
        <p></p>
    </div>

    <div class="w3-col s1">
        <p></p>
    </div>
</div>
<div class="w3-row">
    <div class="w3-col s1">
        <p></p>
    </div>
    <div class="w3-col s10">
    <s:if test="%{hote == true}">
        <s:if test="%{canBeLaunch == 1}">
            <s:form action="lancerPartie">
                <s:hidden name="idPartie" value="%{idPartie}"/>
                <s:submit key="salon.lancerPartie" class="w3-btn w3-center w3-blue-grey"/>
            </s:form>
        </s:if>
    </s:if>

    <s:form action="quitterSalon">
        <s:hidden name="idPartie" value="%{idPartie}"/>
        <s:submit key="salon.quitterSalon" class="w3-btn w3-center w3-blue-grey"/>
    </s:form>
</div>
    <div class="w3-col s1">
        <p></p>
    </div>
</div>
</body>
</html>
