function logout(){
      layer.confirm('确定要登出ROOT吗？',{btn:['退出','再看看']},function(){
        location.href = "/logout";
      });
    }