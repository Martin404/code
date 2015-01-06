<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>招飞初检</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!-- 公共JS&CSS开始 -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href='${pageContext.request.contextPath}/resources/css/style/style.css'/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/aircrewhealthjs/commonUI.js"></script>
    <!-- 公共JS&CSS结束 -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/uploadify.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/uploadify/jquery.uploadify-3.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/uploadify/uploadify.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/json2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/aircrewhealthjs/physical/physical.js"></script>
</head>
<body>
<input id='sessionUID' value='<%=session.getId()%>' type="hidden"/>
    <table id="nhc01_infoGrid2"></table>
    <div id="nhc01_editInfoDiv2" style="padding: 5px; width: 890px; height: 457px;">
    <fieldset>
    <legend>体检录入</legend>
    <form id="fileUploadForm" name = "fileUploadForm" method="post" action="physical.do?method=physicalInitSave" enctype="multipart/form-data"> 
    <table class="divTable">
    <tr>
    <td>附件名:</td>
    <td><input id="tempFileName" readonly="readonly" class="easyui-validatebox" required="true" style="width:300px;_width:300px; " />
    <input id="personnel" name="personnel" type="hidden"/>
    </td>
    </tr>
    <tr>
    <td>参检人员明细表:</td>
    <td><input type="file" name="uploadify" id="file_upload" /><hr>  
    <a class="easyui-linkbutton" onclick="startUpload('personnel');" href="javascript:void(0);">开始上传</a>   
    <a href="javascript:$('#file_upload').uploadify('cancel', '*')" class="easyui-linkbutton">取消所有上传</a>   
    </td>
    </tr>  
    </table>
    <div id="viewBtn">
    <a class="easyui-linkbutton" icon="icon-ok" onclick="submitFormC()">保存</a>
    <a class="easyui-linkbutton" icon="icon-redo" id="Init_rest"> 重置</a>
    </div>
     </form>
    </fieldset>
    </div>
</body>
</html>
