import kxg.searchaf.Role
import kxg.searchaf.User
import kxg.searchaf.UserRole

class BootStrap {

	def init = { servletContext ->
 		//def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
// 		def userRole = new Role(authority: 'ROLE_USER').save(flush: true)//

//		def testUser = new User(username: 'admin', password: 'admin',enabled:true,accountExpired:false,accountLocked:false,passwordExpired:false,email:"132@163.com")
//		testUser.save(flush: true)//

//		UserRole.create testUser, adminRole, true//

//		def testUser1 = new User(username: 'test1', password: 'test1',enabled:true,accountExpired:false,accountLocked:false,passwordExpired:false,email:"12321@163.com")
//		testUser1.save(flush: true)//

//		UserRole.create testUser1, userRole, true
	}
	def destroy = {
	}
}
