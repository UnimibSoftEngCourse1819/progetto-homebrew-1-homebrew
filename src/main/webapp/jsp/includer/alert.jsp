<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:if test="${alertMessage != null && alertType != null}">
	<div class="modal fade" id="alert_modal" tabindex="-1" role="dialog"
		aria-labelledby="alert_modal_label" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content ${alertType}">
				<div class="modal-body">
					<p>${alertMessage}</p>
				</div>
			</div>
		</div>
	</div>
</c:if>


