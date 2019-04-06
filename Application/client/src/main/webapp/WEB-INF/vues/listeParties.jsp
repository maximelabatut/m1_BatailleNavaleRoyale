<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Jonathan
  Date: 14/12/2017
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="./css/w3.css">
<head>
    <title><s:text name="listeParties.title"/><s:property value="pseudo"/></title>
    <META HTTP-EQUIV="Refresh" CONTENT="5">
</head>
<body class="w3-teal">

<s:if test="%{listeParties.size() == 0}">
    <h1 class="w3-center"><s:text name="listeParties.aucunePartie"/></h1>
</s:if>
<s:else>
    <h1 class="w3-center"><s:text name="listeParties.text"/></h1>

    <div class="w3-row">
        <div class="w3-col s1">
            <p></p>
        </div>
        <div class="w3-col s10">
            <s:if test="%{listeParties.size() == 0}">
                <h2><s:text name="listeParties.aucunePartiesJouables"/></h2>
            </s:if>
            <s:else>
                <h2><s:text name="listeParties.listePartiesJouables"/></h2>
                <s:form action="rejoindrePartie">
                    <s:select list="listeParties" name="idPartie" listKey="idPartie"
                              listValue="%{'Partie de '+ hote.pseudo + ' - ' + mesJoueurs.size() + '/' + nbJoueurAttendu + ' joueur(s)'}"
                              class="w3-select"/>
                    <s:submit key="listeParties.rejoindre" class="w3-btn w3-center w3-blue-grey w3-margin-top"/>
                </s:form>
            </s:else>
        </div>
        <div class="w3-col s1">
            <p></p>
        </div>
    </div>
</s:else>
<br/>
<div class="w3-row">
    <div class="w3-col s1">
        <p></p>
    </div>
    <div class="w3-col s10">
        <s:form action="retourMenu">
            <s:submit key="listeParties.retour" class="w3-btn w3-center w3-blue-grey"/>
        </s:form></div>
    <div class="w3-col s1">
        <p></p>
    </div>
</div>
</body>
</html>
