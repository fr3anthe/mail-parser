Для тестирования используется cURL (https://curl.haxx.se/download.html)

Ниже приведены команды для тестирования (Windows):

	horyzontal type
		curl -F "file=@path\to\file" http://localhost:8080/mail/1
		
	vertical type
		curl -F "file=@path\to\file" http://localhost:8080/mail/2
		
	text type
		curl -F "file=@path\to\file" http://localhost:8080/mail/3
