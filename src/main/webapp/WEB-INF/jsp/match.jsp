<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ include file="../jspf/header.jspf"%>

<div class="starter-template">

	<h3>Usuario</h3>

	<hr />
	<div class="row">
		<div class="col-sm-3 col-xs-3">
			<img src="${s}/img/perfil.jpg"
				class="img-rounded img-responsive img-center">
		</div>
		<div class="col-sm-9 col-xs-9">
			<div class="panel panel-default panel-match">
				<div class="panel-body">
					<div class="col-sm-5">Nombre</div>
					<div class="col-sm-7">Nombre de usuario</div>
				</div>
			</div>
			<div class="panel panel-default panel-match">
				<div class="panel-body">
					<div class="col-sm-5">Edad</div>
					<div class="col-sm-7">25</div>
				</div>
			</div>
			<div class="panel panel-default panel-match">
				<div class="panel-body">
					<div class="col-sm-5">Ciudad</div>
					<div class="col-sm-7">Madrid</div>
				</div>
			</div>
		</div>
	</div>

	<hr />

	<div>
		<h6>Información</h6>
		<p>Cum sociis natoque penatibus et magnis dis parturient montes,
			nascetur ridiculus mus.</p>
	</div>

	<hr />

	<!-- Trigger the modal with a button -->
	<button type="button" class="btn btn-block btn-info btn-lg" data-toggle="modal"
		data-target="#myModal2" style="background-color: #13c1ac;color:">Enviar
		mensaje</button>

	<!-- Modal -->
	<div id="myModal2" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header" style="background-color: #13c1ac;color: white;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Mensaje para Usuario</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<textarea class="form-control" rows="5" id="comment" style="width: 100%;resize: none;"></textarea>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						style="background-color: #13c1ac">Enviar</button>
				</div>
			</div>

		</div>
	</div>

	<hr />

	<div>
		<h6>Fotos</h6>
		<div class="row">
			<div class="col-md-3 col-xs-3">
				<div class="thumbnail">
					<img src="${s}/img/perfil.jpg"
						onclick="openModal();currentSlide(1)" class="hover-shadow"
						style="width: 100%">
				</div>
			</div>
			<div class="col-md-3 col-xs-3">
				<div class="thumbnail">
					<img src="${s}/img/perfil.jpg"
						onclick="openModal();currentSlide(2)" class="hover-shadow"
						style="width: 100%">
				</div>
			</div>
			<div class="col-md-3 col-xs-3">
				<div class="thumbnail">
					<img src="${s}/img/perfil.jpg"
						onclick="openModal();currentSlide(3)" class="hover-shadow"
						style="width: 100%">
				</div>
			</div>
			<div class="col-md-3 col-xs-3">
				<div class="thumbnail">
					<img src="${s}/img/perfil.jpg"
						onclick="openModal();currentSlide(4)" class="hover-shadow"
						style="width: 100%">
				</div>
			</div>
			<div class="col-md-3 col-xs-3">
				<div class="thumbnail">
					<img src="${s}/img/perfil.jpg"
						onclick="openModal();currentSlide(5)" class="hover-shadow"
						style="width: 100%">
				</div>
			</div>
		</div>
		<!-- The Modal/Lightbox -->
		<div id="myModal" class="modal-img-match">
			<span class="close-img-match cursor" onclick="closeModal()">&times;</span>
			<div class="modal-content-img-match">

				<div class="mySlides">
					<div class="numbertext">1 / 5</div>
					<img src="${s}/img/perfil.jpg" class="img-match">
				</div>

				<div class="mySlides">
					<div class="numbertext">2 / 5</div>
					<img src="${s}/img/perfil.jpg" class="img-match">
				</div>

				<div class="mySlides">
					<div class="numbertext">3 / 5</div>
					<img src="${s}/img/perfil.jpg" class="img-match">
				</div>

				<div class="mySlides">
					<div class="numbertext">4 / 5</div>
					<img src="${s}/img/perfil.jpg" class="img-match">
				</div>

				<div class="mySlides">
					<div class="numbertext">5 / 5</div>
					<img src="${s}/img/perfil.jpg" class="img-match">
				</div>

				<!-- Next/previous controls -->
				<a class="prev" onclick="plusSlides(-1)">&#10094;</a> <a
					class="next" onclick="plusSlides(1)">&#10095;</a>

				<!-- Caption text -->
				<div class="caption-container">
					<p id="caption"></p>
				</div>
			</div>
		</div>
	</div>

	<hr />

	<%@ include file="../jspf/authinfo.jspf"%>
</div>

<%@ include file="../jspf/footer.jspf"%>
