<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: anthonylabbe
  Date: 16/04/2018
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="./css/w3.css">
<head>
    <title><s:text name="Tour Joueur"/></title>
</head>
<body class="w3-teal">
<h1 class="w3-center">Tour Joueur </h1>



<div class="w3-row">
    <div class="w3-col s1">
        <p></p>
    </div>
    <div class="w3-col s10">
        <table style="width: 100px">
            <s:iterator value="monPlateau.lePlateau" status="ptr1" var="ligne">
                <tr>
                    <s:iterator value="ligne" status="ptr2" var="case">
                        <td>
                            <div class="w3-display-container">
                                <img src="<s:property value="#case.imgPath"/>"/>
                            </div>
                        </td>
                    </s:iterator>
                </tr>
            </s:iterator>
        </table>
    </div>
    <div class="w3-col s1">
        <p></p>
    </div>
</div>
<br>

    <div class="w3-col s1">
        <p></p>
    </div>


</body>
</html>

