/**
 * Created by 孙伟飞
 * on 2018/4/3.
 */

var app = angular.module('module',[]);
app.controller('HomeController',function ($scope,$http) {

        //去除前后空格
        String.prototype.trim=function() {
        return this.replace(/(^\s*)|(\s*$)/g, '');
        };
    var LODOP; //声明为全局变量
     $scope.sendSfzh=function(){

         var  sfz = document.getElementById('input');
         //获取消息显示框
         var dio = document.getElementById('Dialog');
         //获取搜索框
         var searchInput = document.getElementById('btn1');
        if(sfz.value.length == 18){

            $http({
                url:'../queuingSystem/quhao/'+sfz.value,
                method:'POST'
            }).success(function (data) {
                if (data.MSG){
                    dio.innerHTML=data.MSG;
                    setTimeout(function () {
                        dio.innerHTML='';
                    },2500);
                    return false;
                }
                //将排序号（data.RECORD.number）从一位变四位
                var number1 = data.RECORD.number;
                strNum= number1.toString();
                while (strNum.length < 4 ){
                    strNum= '0'+strNum;
                }
                //将生成的新的排序号赋值给data.RECORD.number
                data.RECORD.number = strNum;
                $scope.data=data.RECORD;
                setTimeout(function () {
                    LODOP=getLodop();
                    var html=document.getElementById('dayincontent').innerHTML;
                    var html = '<style>'+document.getElementById('style1').innerHTML+'</style>'+html;
                    LODOP.SET_PRINT_PAGESIZE(1,"1500","1200","");
                    LODOP.ADD_PRINT_HTM(2, 20, "90%", "100%", html);
                    LODOP.PRINT();
                    location.reload();
                },500);
            })
        }else{
                dio.innerHTML="无效身份证！";
                setTimeout(function () {
                    dio.innerHTML='';
                },10000);
                sfz.focus();


            //return false;
        }
    };
    $scope.print=function () {
        print();
    }

});


