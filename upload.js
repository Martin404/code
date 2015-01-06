function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    projectName = "/AircrewHealth";
    return(localhostPaht+projectName);
};

$(function(){
    uploadify();
});
var idName="";
function uploadify(){
    $("#file_upload").uploadify({  
        'height'        : 27,   
        'width'         : 80,    
        'buttonText'    : '添加附件',  
        'swf'           : getRootPath()+'/resources/js/uploadify/uploadify.swf?ver=' + Math.random(),  
        'uploader'      : getRootPath()+'/upload.do;jsessionid='+$("#sessionUID").val()+'?method=upload',  
        'auto'          : false, 
        'fileSizeLimit' : '30720KB', 
        'fileTypeExts'  : '*.doc; *.jpg; *.rar', 
        'cancelImg' :  getRootPath()+'/resources/js/uploadify/uploadify-cancel.png',
        'uploadLimit' : 3, 
       // 'formData'      : {'userName':'','content':''},  
        'onUploadStart' : function(file) {
        },  
        'onUploadSuccess':function(file, data, response){  
            //alert('The file ' + file.name + ' was successfully uploaded with a response of ' + response + ':' + data);
             $("#tempFileName").val(file.name);
             $("#"+idName).val(data);
        },  
        'onUploadComplete':function(){  
           // $('#importLispDialog').window('close');  
        }  
    });  
}
function startUpload(name){  
            idName=name;    
         $('#file_upload').uploadify('upload','*');  
}  
