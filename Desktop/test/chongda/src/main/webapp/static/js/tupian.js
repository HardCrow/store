$(function () {

    //更改头像
    $('#updateImg').click(function () {
        var $file=$('#file');
        //触发文件按钮的点击事件
        $file.click();

        //选择文件后，获取上传的图片
        $file.change(function () {
            //获取上传的图片
            var file=$file[0].files[0];
            //检验是否为图像
            if(!/image\/\w+/.test(file.type)){
                alert("抱歉，只支持图片类型哦！");
                return false;
            }
            if(file.size/1024>65){
                alert('文件太大，必须小于65k！');
                return false;
            }
            var reader = new FileReader();
            //将文件以Data URL形式读入页面
            reader.readAsDataURL(file);
            reader.onload=function () {
                //将img中的图像更改成更改后的图像，并上传
                $('.img img').attr('src',this.result);
            };
            var id=1;//获取id，这里假设为1
            //利用formData传送数据
            var data=new FormData();
            data.append("id",id);
            data.append("file",file);
            $.ajax({
                url:"${pageScope.basePath}user/uploadImg",
                data:data,
                //上传文件无需缓存
                cache: false,
                contentType:false, //- 必须false才会自动加上正确的Content-Type
                processData: false, //- 必须false才会避开jQuery对 formdata 的默认处理,XMLHttpRequest会对 formdata 进行正确的处理
                type:'post',
                success:function (result) {
                    if(result.code!==200){
                        //上传成功
                    }else{
                        alert(result.extend.error);
                        //取消更改，将数据库原来的图像设置
                        $('.img').empty();
                        $('.img').append($("    <img src=\"${pageScope.basePath}user/img?id="+id+"\" style=\"width: 50px;height:50px;border-radius: 25px;display: inline-block;background-position: center center;\" alt=\"\">"));
                    }
                }
            });
        });



    });

});
