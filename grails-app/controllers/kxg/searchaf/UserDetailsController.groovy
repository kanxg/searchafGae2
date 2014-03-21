package kxg.searchaf

//import grails.plugin.springsecurity.annotation.Secured
//import org.springframework.dao.DataIntegrityViolationException

//@Secured(['ROLE_USER'])
class UserDetailsController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def springSecurityService


	def index() {
		//		println springSecurityService.principal.id
		redirect(action: "show")
	}

	//	def list(Integer max) {
	//		params.max = Math.min(max ?: 10, 100)
	//		[userDetailsInstanceList: UserDetails.list(params), userDetailsInstanceTotal: UserDetails.count()]
	//	}

	//	def create() {
	//		[userDetailsInstance: new UserDetails(params)]
	//	}

	//	def save() {
	//		def userDetailsInstance = new UserDetails(params)
	//		if (!userDetailsInstance.save(flush: true)) {
	//			render(view: "create", model: [userDetailsInstance: userDetailsInstance])
	//			return
	//		}
	//
	//		flash.message = message(code: 'default.created.message', args: [
	//			message(code: 'userDetails.label', default: 'UserDetails'),
	//			userDetailsInstance.id
	//		])
	//		redirect(action: "show", id: userDetailsInstance.id)
	//	}

	def show() {
		def user= springSecurityService.getCurrentUser()
		def userDetailsInstance = UserDetails.findByUser(user)
		if (!userDetailsInstance) {
			userDetailsInstance = new UserDetails();
			userDetailsInstance.user=user
			userDetailsInstance.save(flush: true)
		}

		[userDetailsInstance: userDetailsInstance , email:user.email]
	}

	def edit() {
		def user= springSecurityService.getCurrentUser()
		def userDetailsInstance = UserDetails.findByUser(user)
		if (!userDetailsInstance) {
			userDetailsInstance = new UserDetails();
			userDetailsInstance.user=user
			userDetailsInstance.save(flush: true)
		}

		[userDetailsInstance: userDetailsInstance  , email:user.email]
	}

	def update(Long id, Long version) {
		def userDetailsInstance = UserDetails.get(id)
		if (!userDetailsInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'userDetails.label', default: 'UserDetails'),
				id
			])
			redirect(action: "view")
			return
		}

		if (version != null) {
			if (userDetailsInstance.version > version) {
				userDetailsInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[
							message(code: 'userDetails.label', default: 'UserDetails')] as Object[],
						"Another user has updated this UserDetails while you were editing")
				render(view: "edit", model: [userDetailsInstance: userDetailsInstance])
				return
			}
		}

		userDetailsInstance.properties = params

		if (!userDetailsInstance.save(flush: true)) {
			render(view: "edit", model: [userDetailsInstance: userDetailsInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [
			message(code: 'userDetails.label', default: 'UserDetails'),
			userDetailsInstance.id
		])
		redirect(action: "show", id: userDetailsInstance.id)
	}

	//	def delete(Long id) {
	//		def userDetailsInstance = UserDetails.get(id)
	//		if (!userDetailsInstance) {
	//			flash.message = message(code: 'default.not.found.message', args: [
	//				message(code: 'userDetails.label', default: 'UserDetails'),
	//				id
	//			])
	//			redirect(action: "list")
	//			return
	//		}
	//
	//		try {
	//			userDetailsInstance.delete(flush: true)
	//			flash.message = message(code: 'default.deleted.message', args: [
	//				message(code: 'userDetails.label', default: 'UserDetails'),
	//				id
	//			])
	//			redirect(action: "list")
	//		}
	//		catch (DataIntegrityViolationException e) {
	//			flash.message = message(code: 'default.not.deleted.message', args: [
	//				message(code: 'userDetails.label', default: 'UserDetails'),
	//				id
	//			])
	//			redirect(action: "show", id: id)
	//		}
	//	}
}
