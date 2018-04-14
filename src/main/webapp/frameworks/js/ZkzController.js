/**
 * Created by 孙伟飞 on 2018/4/9.
 */
var app = angular.module('module',[]);

app.controller('ZkzController',function ($scope,$http) {
    // url:'../queuingSystem/enter/'+zkzh,
    $scope.functionzkzh = function (zkzh) {
        var zkz = document.getElementById('inputZkz');
        var viewContent = document.getElementById('viewContent');
        if(zkz.value.length == 10 ){
            $http({
                url:'../queuingSystem/enter/'+zkzh,
                method:'POST'
            }).success(function (data) {
                if(data.MSG){
                    viewContent.innerHTML=data.MSG;
                    setTimeout(function () {
                        viewContent.innerHTML='';
                    },2000)
                }
                var number1 = data.RECORD.number;
                strNum= number1.toString();
                while (strNum.length < 4 ){
                    strNum= '0'+strNum;
                }
                //将生成的新的排序号赋值给data.RECORD.number
                data.RECORD.number = strNum;
                $scope.ksInfor=data.RECORD;
                setTimeout(function () {
                    zkz.value='';
                },1000);
            })
        }

    }
});