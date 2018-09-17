$(function(){

	$('.js-busca-produto').on('blur', function(event) {
		var inputCodigo = $(event.currentTarget);
		var codigoProduto = $("#codigoProduto").val();
		var urlBusca = inputCodigo.attr('href')+ codigoProduto;
		
		if(codigoProduto != '' || codigoProduto != null){
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
			});
			response.fail(function(e) {
				limpaCampos(false);
			});
		}
		
		
	});
	
		
	
	$('.js-limpa-produto').on('click', function(event) {
		event.preventDefault();
		limpaCampos(true);
	});
	
});


function limpaCampos(isBotao) {
	
	if(isBotao){
		$('#codigoProduto').val('');
	}
	$('#idProduto').val('');
	$('#descricao').val('');
	$('#valor').val('');
}
