<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ include file="../jspf/header.jspf"%>

<div class="starter-template">
	
	<div class="tile">
		<h1>Quest 1</h1>
		
		<div>
			<ul class="pagination">
				<li class="previous"><a href="#fakelink" class="fui-arrow-left"></a></li>
	            <li class="active"><a href="#fakelink">1</a></li>
	            <li><a href="#fakelink">2</a></li>
	            <li><a href="#fakelink">3</a></li>
	            <li><a href="#fakelink">4</a></li>
	            <li class="next"><a href="#fakelink" class="fui-arrow-right"></a></li>
            </ul>
		
		</div>
		<div class="historia">
			<p>En un lugar de la Mancha, de cuyo nombre no quiero acordarme,
				no ha mucho tiempo que vivía un hidalgo de los de lanza en astillero,
				adarga antigua, rocín flaco y galgo corredor. Una olla de algo más
				vaca que carnero, salpicón las más noches, duelos y quebrantos los
				sábados, lantejas los viernes, algún palomino de añadidura los
				domingos, consumían las tres partes de su hacienda. El resto della
				concluían sayo de velarte, calzas de velludo para las fiestas, con
				sus pantuflos de lo mesmo, y los días de entresemana se honraba con
				su vellorí de lo más fino.
			</p>
			
			<div class="row">
				<div class="col-md-4 col-xs-12">
					<a class="btn btn-block btn-main-color" href="#">Respuesta1</a>
				</div>
				<div class="col-md-4 col-xs-12">
					<a class="btn btn-block btn-main-color" href="#">Respuesta2</a>
				</div>
				<div class="col-md-4 col-xs-12">
					<a class="btn btn-block btn-main-color" href="#">Respuesta3</a>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="../jspf/authinfo.jspf"%>		
</div>

<%@ include file="../jspf/footer.jspf"%>
