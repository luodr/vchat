function MathRand2(){
    var num="";
    for(var i=0;i<6;i++){
        num+=Math.floor(Math.random()*10);
    }
    return num
}
module.exports = {
    MathRand2
}