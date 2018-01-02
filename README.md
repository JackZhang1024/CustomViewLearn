# CustomViewLearn
自定义View学习

各种控件
各种动画

在使用高版本的SDK的时候 我们可以使用@TargetApi的屏蔽掉AndroidLint的检查
同时我们在代码里面加上版本判断 如果高于某个版本 则我们使用高版本的API
如果低于某个版本则我们使用折中方式处理
@TargetApi(11)
public void method(){
       if(Build.VERSION.SDK_INT > 11){
       }else{
       
       }
       

}

