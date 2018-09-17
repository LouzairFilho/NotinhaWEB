function trocaPfPj() {
				var i = document.cliente.tipoPessoa.selectedIndex;
				var tipoPessoa = document.cliente.tipoPessoa[i].text;
				var cnpj = "Jurídica";
				var cpf = "Física";
				var elCpfCnpj = document.getElementById('cpfCnpjCliente');
				

				if (tipoPessoa == cnpj) {
					document.getElementById("lbCpfCnpj").innerHTML = "CNPJ";
					document.getElementById("lbnomeRazao").innerHTML = "Razão Social";
					document.getElementById("cpfCnpjCliente").value="";
					elCpfCnpj.setAttribute("onkeypress","mascara(this,cnpj)");
					elCpfCnpj.setAttribute("maxLength","18");
					
				}
				if (tipoPessoa == cpf) {
					document.getElementById("lbCpfCnpj").innerHTML = "CPF";
					document.getElementById("lbnomeRazao").innerHTML = "Nome";
					document.getElementById("cpfCnpjCliente").value="";
					elCpfCnpj.setAttribute("onkeypress","mascara(this,cpf)");
					elCpfCnpj.setAttribute("maxLength","14");
					
				}
			}

$("#celular").bind('input propertychange',function(){
	 
    var texto = $(this).val();
    
    texto = texto.replace(/[^\d]/g, '');
    
    if (texto.length > 0)
    {
    texto = "(" + texto;
        
        if (texto.length > 3)
        {
            texto = [texto.slice(0, 3), ") ", texto.slice(3)].join('');  
        }
        if (texto.length > 12)
        {      
            if (texto.length > 13) 
                texto = [texto.slice(0, 10), "-", texto.slice(10)].join('');
            else
                texto = [texto.slice(0, 9), "-", texto.slice(9)].join('');
        }                 
            if (texto.length > 15)                
               texto = texto.substr(0,15);
    }
   $(this).val(texto);     
});

$("#telefone").bind('input propertychange',function(){
	 
    var texto = $(this).val();
    
    texto = texto.replace(/[^\d]/g, '');
    
    if (texto.length > 0)
    {
    texto = "(" + texto;
        
        if (texto.length > 3)
        {
            texto = [texto.slice(0, 3), ") ", texto.slice(3)].join('');  
        }
        if (texto.length > 12)
        {      
            if (texto.length > 13) 
                texto = [texto.slice(0, 10), "-", texto.slice(10)].join('');
            else
                texto = [texto.slice(0, 9), "-", texto.slice(9)].join('');
        }                 
            if (texto.length > 15)                
               texto = texto.substr(0,15);
    }
   $(this).val(texto);     
});



$(function(){
	
	
	$('.js-busca-cliente').on('blur', function(event) {
		var inputcpfCnpj = $(event.currentTarget);
		var cpfCnpj = $("#cpfCnpjCliente").val();
		var urlBusca;
		var cpfCnpjMask = cpfCnpj;
		var tipoPessoa  = $("#tipoPessoa").val();
		
		if(cpfCnpj != '' || cpfCnpj != null){
			
			cpfCnpj = substituiCarcter(cpfCnpj, '.','');
			cpfCnpj = substituiCarcter(cpfCnpj, '-','');
			cpfCnpj = substituiCarcter(cpfCnpj, '/','');
			
			
			urlBusca = inputcpfCnpj.attr('href')+ cpfCnpj;
			var response = $.ajax({
				url: urlBusca,
				type: 'GET'
			});
			
			response.done(function(e) {
				var obj = e
				
				for(attr in obj) {
			        var el = document.getElementsByName(attr)[0];

			        if(el) {
			            el.value = obj[attr];
			        }
				}
				if (e.tipoPessoa == null) {
					 $("#tipoPessoa").val(tipoPessoa);
				}
				$("#cpfCnpjCliente").val(cpfCnpjMask);
			});
			response.fail(function(e) {
				limpaCampos(false);
			});
		}
		
	});
});

function limpaCampos(isBotao) {
	
	if(isBotao){
		$("#cpfCnpjCliente").val('');
		$("#tipoPessoa").val('');
	}
	$("#idCliente").val('');
	$("#nomeRazao").val('');
	$("#nomeFantasia").val('');
	$("#telefone").val('');
	$("#celular").val('');
	$("#email").val('');
	$("#contato").val('');
	$("#cep").val('');
	$("#endereco").val('');
	$("#bairro").val('');
	$("#cidade").val('');
	$("#uf").val('');
	$("#obs").val('');
	
}



//str -> texto a ser alterado
//crtRmv -> caracter a ser removido
//crtAdd -> caracter a ser adicionado
function substituiCarcter(str,crtRmv,crtAdd){
    return str.split(crtRmv).join(crtAdd);
}