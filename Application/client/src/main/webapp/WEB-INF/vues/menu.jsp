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
    <title><s:text name="menu.title"/><s:property value="pseudo"/></title>
    <META HTTP-EQUIV="Refresh" CONTENT="5;URL=retourMenu.action">
</head>
<body class="w3-teal">

<div>

    <h1 class="w3-center"><s:text name="menu.h1"/><s:property value="pseudo"/>
        <br>
        ---
        <br>
        <s:text name="menu.h1Suite"/>
    </h1>

    <div class="w3-row">
        <div class="w3-col s1">
            <p></p>
        </div>
        <div class="w3-col s10">
            <h2><s:text name="menu.joueursEnLigne"/></h2>
            <ul>
                <s:iterator value="joueursEnLigne">
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
        <s:if test="%{partiesPubliquesEnCours.size() == 0}">
            <div class="w3-col s10">
                <h2><s:text name="menu.aucunePartiesPubliquesEnCours"/></h2>
            </div>
        </s:if>
        <s:else>
            <div class="w3-col s10">
                <h2><s:text name="menu.partiesPubliquesEnCours"/></h2>
                <ul>
                    <s:iterator value="partiesPubliquesEnCours">
                        <li><s:property/></li>
                    </s:iterator>
                </ul>
            </div>
        </s:else>
        <div class="w3-col s1">
            <p></p>
        </div>
    </div>

    <div class="w3-row">
        <div class="w3-col s1">
            <p></p>
        </div>
        <div class="w3-col s10">
            <h2><s:text name="menu.autresFonctions"/></h2>
            <s:form action="goToCreerPartie">
                <s:submit key="menu.creerPartie" class="w3-btn w3-center w3-blue-grey"/>
            </s:form>
            <s:form action="goToListeParties">
                <s:submit key="menu.rejoindrePartie" class="w3-btn w3-center w3-blue-grey"/>
            </s:form>
            <s:form action="deconnexion">
                <s:submit key="menu.deconnexion" class="w3-btn w3-center w3-blue-grey"/>
            </s:form>
        </div>
        <div class="w3-col s1">
            <p></p>
        </div>
    </div>
</div>
</body>
</html>
