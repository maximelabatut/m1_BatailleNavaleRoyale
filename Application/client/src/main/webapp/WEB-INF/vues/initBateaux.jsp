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
    <title><s:text name="initBateaux.title"/></title>
</head>
<body class="w3-teal">

<h1 class="w3-center">
    <s:text name="initBateaux.text"/>
</h1>

<h2 class="w3-left">Légende : 1 -> Tete Haut | 2 -> Tete Droite | 3 -> Tete Bas | 4 -> Tete Gauche</h2>

<div class="w3-row">
    <table>
    <s:iterator value="bateaux" status="nbBateau">
        <tr>
            <td style="text-align: center">
                <s:iterator begin="1" end="largeur">
                {
                <s:iterator begin="1" end="longueur" status="incr">
                    =
                </s:iterator>
                ]<br/>
                </s:iterator>
                <s:property value="id"/>

                    <%--<s:a action="pivoter">--%>
                    <%--<s:param name="bateau" value="id"/>--%>
                    <%--gauche--%>
                    <%--</s:a>--%>
                <s:form action="poserBateau">
                    <s:select list="coordsX" name="posx" key="bateau.x"/>
                    <s:select list="coordsY" name="posy" key="bateau.y"/>
                    <s:radio list="orientation" key="bateau.pivot" name="orientationBateau"  checked="true"/>
                    <s:hidden name="nbBateau" value="%{#nbBateau.index}"/>
                    <s:submit value="Poser ce bateau"/>
                </s:form>
            </td>
        </tr>
    </s:iterator>
    </table>
    <table cellspacing="0" border="1">
        <s:iterator value="plateau" status="x">
            <tr>
                <s:iterator status="y" var="case">
                    <td align="center">
                        <s:if test="x<plateau.length/4 && y<plateau.length/4 && coinPlateau == 1">
                            <img src="<s:property value="#case.imgPath"/>"/>
                        </s:if>
                        <s:else>
                            <s:if test="x<plateau.length/4 && y>=plateau.length/4+plateau.length/2 && coinPlateau == 2">
                                <img src="<s:property value="#case.imgPath"/>"/>
                            </s:if>
                            <s:else>
                                <s:if test="x>=plateau.length/4+plateau.length/2 && y<plateau.length/4 && coinPlateau == 3">
                                    <img src="<s:property value="#case.imgPath"/>"/>
                                </s:if>
                                <s:else>
                                    <s:if test="x>=plateau.length/4 && y>plateau.length/4 && coinPlateau == 4">
                                        <img src="<s:property value="#case.imgPath"/>"/>
                                    </s:if>
                                    <s:else>
                                        <img src="././img/damage.png"/>
                                    </s:else>
                                </s:else>
                            </s:else>
                        </s:else>
                    </td>
                </s:iterator>
            </tr>
        </s:iterator>
    </table>
</div>


</body>
</html>
