<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<% String basePath = request.getScheme() + "://" + request.getServerName() + ":"
        + request.getServerPort() + request.getContextPath() + "/"; %>

<!Doctype html>
<html>
    <head>
        <base href="<%=basePath%>">
        <title>用户管理</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="./assets/css/styles.css">
        <link rel="stylesheet" href="./assets/js/jquery-ui/jquery-ui.min.css">
        <script src="./assets/js/jquery-3.3.1.min.js"></script>
        <script src="./assets/js/jquery.serializejson.min.js"></script>
        <script src="./assets/js/jquery-ui/jquery-ui.min.js"></script>
    </head>
    <body>
        <!-- 用户列表 -->
        <div class="tbl-title">用户列表</div>
        <button onclick="showAddUserModal()">添加新的用户</button>
        <table id="users" class="tbl-list">
            <thead>
            <tr>
                <th>用户名</th>
<%--                <th>照片</th>--%>
                <th>密码</th>
                <th>姓名</th>
                <th>电话</th>
                <th>身份证</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>

        <!-- 添加用户模态框 -->
        <div id="add-user-modal" title="添加用户" style="display:none;">
            <form id="add-user-form">
                <table class="modal-tbl">
                    <tr>
                        <td>用户名</td>
                        <td><input type="text" name="userId"></td>
                    </tr>
                    <tr>
                        <td>密码</td>
                        <td><input type="password" name="password"></td>
                    </tr>
                    <tr>
                        <td>姓名</td>
                        <td><input type="text" name="name"></td>
                    </tr>
                    <tr>
                        <td>电话</td>
                        <td><input type="tel" name="phone"></td>
                    </tr>
                    <tr>
                        <td>身份证</td>
                        <td><input type="text" name="documentnumber"></td>
                    </tr>
                    <tr>
                        <td>人脸照片</td>
                        <td>
                            <input type="text" name="face" id="face">
                            <input type="button" value="浏览文件"
                                   style="border-radius: 0; border-left: 0;"
                                   onclick="$('#file').click();">
                            <input type="file" id="file" accept=".png, .jpg, .jpeg" style="display: none">
                        </td>
                    </tr>
                    <tr>
                        <td>入住时间</td>
                        <td><input type="date" name="checkin_date"></td>
                    </tr>
                    <tr>
                        <td>退房时间</td>
                        <td><input type="date" name="checkout_date"></td>
                    </tr>
                </table>
            </form>
        </div>

        <!--删除用户对话模态框 -->
        <div id="delete-user-modal" title="删除用户" style="display:none;">
            <p>确定删除该用户吗？</p>
        </div>

        <!-- 更新用户模态框 -->
        <div id="update-user-modal" title="更新用户" style="display:none;">
            <form id="update-user-form">
                <table class="modal-tbl">
                    <tr style="display: none">
                        <td>用户名</td>
                        <td><input type="text" name="userId"></td>
                    </tr>
                    <tr>
                        <td>密码</td>
                        <td><input type="password" name="password"></td>
                    </tr>
                    <tr>
                        <td>姓名</td>
                        <td><input type="text" name="name"></td>
                    </tr>
                    <tr>
                        <td>电话</td>
                        <td><input type="tel" name="phone"></td>
                    </tr>
                    <tr>
                        <td>身份证</td>
                        <td><input type="text" name="documentnumber"></td>
                    </tr>
                    <tr>
                        <td>人脸照片</td>
                        <td>
                            <input type="text" name="face" id="face2">
                            <input type="button" value="浏览文件"
                                   style="border-radius: 0; border-left: 0;"
                                   onclick="$('#file2').click();">
                            <input type="file" id="file2" accept=".png, .jpg, .jpeg" style="display: none">
                        </td>

                    </tr>
                    <tr>
                        <td>入住时间</td>
                        <td><input type="date" name="checkin_date"></td>
                    </tr>
                    <tr>
                        <td>退房时间</td>
                        <td><input type="date" name="checkout_date"></td>
                    </tr>

                </table>
            </form>
        </div>

        <!-- 提示信息模态框 -->
        <div id="msg-modal" title="" style="display:none">
            <p></p>
        </div>

        <script>
            // 开始
            $(function () {
                listAllUser();
            });


            // 显示所有用户
            function listAllUser() {
                $.ajax({
                    type: "GET",
                    url: "UserServlet?action=listAll",
                    dataType: "json",
                    success: function (data) {
                        $("#users tbody").empty();  // 每次载入前先清空显示区域，防止数据重复显示
                        var users = data.users;
                        for (var i in users) {
                            var user = users[i];
                            var userStr = JSON.stringify(user);
                            $("#users tbody").append('<tr><td>' + user.userId + '</td>'
                                // + '<td> <img style="width: 100px;height: 100px;" src="uploads/' + user.face + '" alt=""></td>'
                                + '<td>' + user.password + '</td>'
                                + '<td>' + user.name + '</td>'
                                + '<td>' + user.phone + '</td>'
                                + '<td>' + user.documentnumber + '</td>'
                                + '<td><button onclick=\'showUpdateUserModal(' + userStr + ')\'>更新</button> '
                                + '<button onclick="deleteUser(\'' + user.userId + '\')">删除</button></td></tr>');
                        }
                    },
                    error: function () {
                        console.log("ajax error");
                    }
                });
            }

            // 显示添加用户模态框
            function showAddUserModal() {
                $("#add-user-form")[0].reset();  // 每次载入前先清空表单，防止显示之前的信息
                $("#add-user-modal").dialog({
                    resizable: false,
                    modal: true,
                    buttons: {
                        "添加": function () {
                            addUser();
                        },
                        "取消": function () {
                            $(this).dialog("close");
                        },
                    },
                });
            }

            // 添加用户
            function addUser() {
                // 获取序列化表单信息
                var user = $("#add-user-form").serializeJSON();
                var userStr = JSON.stringify(user);

                $.ajax({
                    type: "POST",
                    url: "UserServlet",
                    data: userStr,
                    dataType: "json",
                    success: function (data) {
                        if (data.isSuccess) {
                            $("#add-user-modal").dialog("close");
                            showMsg('添加成功！');
                            listAllUser();
                        } else {
                            $("#add-user-modal").dialog("close");
                            showMsg('添加失败！');
                            listAllUser();
                        }
                    },
                    error: function () {
                        console.log("ajax error");
                    },
                });
            }

            // 删除用户
            function deleteUser(id) {
                // 将id封装为JSON格式数据
                var data = {};
                data.userId = id;
                var dataStr = JSON.stringify(data);
                // 显示删除用户模态框
                $("#delete-user-modal").dialog({
                    resizable: false,
                    modal: true,
                    buttons: {
                        "确认": function () {
                            $.ajax({
                                type: "DELETE",
                                url: "UserServlet",
                                data: dataStr,
                                dataType: "json",
                                success: function (data) {
                                    if (data.isSuccess) {
                                        $("#delete-user-modal").dialog("close");
                                        showMsg('删除成功！');
                                        listAllUser();
                                    } else {
                                        $("#delete-user-modal").dialog("close");
                                        showMsg('删除失败！');
                                        listAllUser();
                                    }
                                },
                                error: function () {
                                    console.log("ajax error");
                                },
                            });
                        },
                        "取消": function () {
                            $(this).dialog("close");
                        },
                    },
                });
            }

            //显示更新用户模态框
            function showUpdateUserModal(user) {
                console.log(user);
                $("#update-user-form")[0].reset();  // 每次载入前先清空表单，防止显示之前的信息
                // 表单赋值
                $("#update-user-form input[name='userId']").val(user.userId);
                $("#update-user-form input[name='password']").val(user.password);
                $("#update-user-form input[name='name']").val(user.name);
                $("#update-user-form input[name='phone']").val(user.phone);
                $("#update-user-form input[name='documentnumber']").val(user.documentnumber);
                $("#update-user-form input[name='face']").val(user.face);
                $("#update-user-form input[name='checkin_date']").val(user.checkin_date);
                $("#update-user-form input[name='checkout_date']").val(user.checkout_date);

                $("#update-user-modal").dialog({
                    resizable: false,
                    modal: true,
                    buttons: {
                        "提交": function () {
                            updateUser();
                        },
                        "取消": function () {
                            $(this).dialog("close");
                        },
                    },
                });
            }

            // 更新用户
            function updateUser() {
                // 获取序列化表单信息
                var user = $("#update-user-form").serializeJSON();
                var userStr = JSON.stringify(user);
                console.log(userStr);
                $.ajax({
                    type: "PUT",
                    url: "UserServlet",
                    data: userStr,
                    contentType: 'application/json;charset=utf-8',
                    dataType: "json",
                    success: function (data) {
                        if (data.isSuccess) {
                            $("#update-user-modal").dialog("close");
                            showMsg('更新成功！');
                            listAllUser();
                        } else {
                            $("#update-user-modal").dialog("close");
                            showMsg('更新失败！');
                            listAllUser();
                        }
                    },
                    error: function () {
                        console.log("ajax error");
                    },
                });
            }

            //显示提示信息
            function showMsg(text) {
                $("#msg-modal p").text(''); // 每次载入前先清空显示区域，防止显示之前的信息
                $("#msg-modal p").text(text);
                $("#msg-modal").dialog({
                    modal: true,
                });
                // 2s后消失
                setTimeout(function () {
                    $("#msg-modal").dialog("close")
                }, 2000);
            }


            // 添加图片上传
            $('body').on('change', '#file', function () {
                var formData = new FormData();
                var files = $($(this))[0].files[0];
                formData.append("file", files);
                $.ajax({
                    url: 'UploadServlet',
                    type: 'POST',
                    data: formData,
                    processData: false,
                    contentType: false,
                    dataType: 'text',
                    success: function (res) {
                        console.log(res);
                        if (res != "") {
                            alert('上传成功');
                        }
                        $('#face').val(res);
                    }
                    , error: function (res) {
                        // alert('错误');
                    }
                });
            })

            // 更新文件上传
            $('body').on('change', '#file2', function () {
                var formData = new FormData();
                var files = $($(this))[0].files[0];
                formData.append("file", files);
                $.ajax({
                    url: 'UploadServlet',
                    type: 'POST',
                    data: formData,
                    processData: false,
                    contentType: false,
                    dataType: 'text',
                    success: function (res) {
                        console.log(res);
                        if (res != "") {
                            alert('上传成功');
                        }
                        $('#face2').val(res);
                    }
                    , error: function (res) {
                        // alert('错误');
                    }
                });
            })
        </script>
    </body>
</html>
