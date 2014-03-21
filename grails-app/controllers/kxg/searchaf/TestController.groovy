package kxg.searchaf

class TestController {

	def index () {
		sendMail {
			//from "watch_af_men@163.com"
			to "xingang.kan@gmail.com"
			subject "Hello from the sendmail plugin"
			body "Hello from the sendmail plugin.  Here's the email you wanted."
		}

		render "EMail sent."
	}
}
