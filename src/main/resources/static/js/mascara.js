function mascara(o,f){
    v_obj=o
    v_fun=f
    setTimeout("execmascara()",1)
}

function execmascara(){
    v_obj.value=v_fun(v_obj.value)
}

function leech(v){
    v=v.replace(/o/gi,"0")
    v=v.replace(/i/gi,"1")
    v=v.replace(/z/gi,"2")
    v=v.replace(/e/gi,"3")
    v=v.replace(/a/gi,"4")
    v=v.replace(/s/gi,"5")
    v=v.replace(/t/gi,"7")
    return v
}

function soNumeros(v){
    return v.replace(/\D/g,"")
}

function telefone(v){
    v=v.replace(/\D/g,"")                 //Remove tudo o que não é dígito
    v=v.replace(/^(\d\d)(\d)/g,"($1) $2") //Coloca parênteses em volta dos dois primeiros dígitos
    v=v.replace(/(\d{4})(\d)/,"$1-$2")    //Coloca hífen entre o quarto e o quinto dígitos
    return v
}

function cpf(v){
    v=v.replace(/\D/g,"")                    //Remove tudo o que não é dígito
    v=v.replace(/(\d{3})(\d)/,"$1.$2")       //Coloca um ponto entre o terceiro e o quarto dígitos
    v=v.replace(/(\d{3})(\d)/,"$1.$2")       //Coloca um ponto entre o terceiro e o quarto dígitos
                                             //de novo (para o segundo bloco de números)
    v=v.replace(/(\d{3})(\d{1,2})$/,"$1-$2") //Coloca um hífen entre o terceiro e o quarto dígitos
    return v
}


function ceps(v){
	 v=v.replace(/D/g,"")                //Remove tudo o que não é dígito
	 v=v.replace(/^(\d{5})(\d)/,"$1-$2") //Esse é tão fácil que não merece explicações
	 return v
}

function cnpj(v){
    v=v.replace(/\D/g,"")                           //Remove tudo o que não é dígito
    v=v.replace(/^(\d{2})(\d)/,"$1.$2")             //Coloca ponto entre o segundo e o terceiro dígitos
    v=v.replace(/^(\d{2})\.(\d{3})(\d)/,"$1.$2.$3") //Coloca ponto entre o quinto e o sexto dígitos
    v=v.replace(/\.(\d{3})(\d)/,".$1/$2")           //Coloca uma barra entre o oitavo e o nono dígitos
    v=v.replace(/(\d{4})(\d)/,"$1-$2")              //Coloca um hífen depois do bloco de quatro dígitos
    return v
}

function data(v){
	 	v=v.replace(/\D/g,"")                    //Remove tudo o que não é dígito
	    v=v.replace(/(\d{2})(\d)/,"$1-$2")       //Coloca um ponto entre o terceiro e o quarto dígitos
	    v=v.replace(/(\d{2})(\d)/,"$1-$2")       //Coloca um ponto entre o terceiro e o quarto dígitos
	                                             //de novo (para o segundo bloco de números)
	    return v
}

function moeda(v){
	v = v.replace(/\D/g, "");
	v = v.replace(/[0-9]{15}/, "invÃ¡lido");
	v = v.replace(/(\d{1})(\d{11})$/, "$1,$2"); // coloca virgula antes dos
	// Ãºltimos 11 digitos
	v = v.replace(/(\d{1})(\d{8})$/, "$1,$2"); // coloca virgula antes dos
	// Ãºltimos 8 digitos
	v = v.replace(/(\d{1})(\d{5})$/, "$1,$2"); // coloca virgula antes dos
	// Ãºltimos 5 digitos
	v = v.replace(/(\d{1})(\d{1,2})$/, "$1.$2"); // coloca ponto antes dos
	// Ãºltimos 2 digitos
	return v;
	}

function imprimirNotinha() {
	var conteudo = document.getElementById('div-notinha').innerHTML,
	tela_impressao = window.open('about:blank');
	
	tela_impressao.document.write(conteudo);
	tela_impressao.window.print();
	tela_impressao.window.close();
}