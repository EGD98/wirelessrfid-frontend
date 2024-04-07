//exampleFormControlInput1 exampleFormControlInput1

//alert("funciona"); email asunto msg

const correo= document.getElementById('email');
const asunto=document.getElementById('asunto');
const msg=document.getElementById('msg');
const form=document.getElementById('form');
const parrafo=document.getElementById('advertencia');
let respuesta=document.getElementById('respuesta');
function enviar(){
    console.log("Estoy aqui ");
    let datos= new FormData(form);
    fetch('correo2.php',{
        method:'POST',
        body:datos
    }).then(res=> res.json()).then(data=> {
        console.log(data);
        console.log("estoy en el fectch");
        if(data=== "OK"){
            alert("Se envio el mensaje con exito");
        }else(alert("Intentalo después"));


    })

}
form.addEventListener("submit",e=> {
e.preventDefault();
let entrar=false;
let advertencias="";
let regEX=/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.([a-zA-Z]{2,4})+$/;
//console.log(regEX.test(email.value));
parrafo.innerHTML="";
if(!regEX.test(email.value)){
    //advertencia+="Email muy corto ";
    advertencias +="El email no es valido <br>";
    entrar=true;
}else{}
if(asunto.value.length <3){
    advertencias+="No haz ingresado un asunto <br>";
    entrar=true;
}
if(msg.value.length < 4){
    advertencias+="Mensaje muy corto <br>";
    entrar=true;
}
if(entrar){
parrafo.innerHTML=advertencias;
}else{
    enviar();
    asunto.value="";
    email.value="";
    msg.value="";


}


});