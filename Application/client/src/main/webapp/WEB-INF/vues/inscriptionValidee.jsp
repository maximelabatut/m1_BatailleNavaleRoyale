<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Florian
  Date: 21/12/2017
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="../css/w3.css">
<link rel="stylesheet" href="../css/style.css">
<head>
    <title><s:text name="inscriptionValidee.title"/></title>
</head>
<body class="w3-teal">

<h1 class="w3-center">
    <s:text name="inscriptionValidee.text"/>
</h1>

<div class="w3-row">
    <div class="w3-col s1">
        <p></p>
    </div>
    <div class="w3-col s10">
        <p>
            <s:text name="inscriptionValidee.instruction"/>
            <s:property value="pseudoInscription"/>.
        </p>
        <br>
        <p>
            <s:text name="inscriptionValidee.instruction.suite"/>
        </p>
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
        <s:form action="inscription">
            <s:hidden name="pseudo" value="%{pseudoInscription}"/>
            <s:submit key="inscriptionValidee.retourAccueil" class="w3-btn w3-center w3-blue-grey"/>
        </s:form>
    </div>
    <div class="w3-col s1">
        <p></p>
    </div>
</div>

<div class="w3-display-bottommiddle">
    <p><s:text name="lesDevs"/></p>
</div>

</body>
</html>
