function Mascara_Hora(Hora, campo){
   var hora01 = '';
   hora01 = hora01 + Hora;
   if (hora01.length == 2){
      hora01 = hora01 + ':';
      campo.value = hora01;
   }
   if (hora01.length == 5){
      Verifica_Hora(campo);
   }
}
function Verifica_Hora(campo){
   hrs = (campo.value.substring(0,2));
   min = (campo.value.substring(3,5));
   estado = "";
   if ((hrs < 00 ) || (hrs > 23) || ( min < 00) ||( min > 59)){
      estado = "errada";
   }
   if (campo.value == "") {
      estado = "errada";
   }
   if (estado == "errada") {
      alert("Hora invalida!");
      campo.focus();
   }
}