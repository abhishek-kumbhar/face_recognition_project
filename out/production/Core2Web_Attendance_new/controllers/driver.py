def imageString( x ):
    return ''.join(x) + "_image = face_recognition.load_image_file(\"" + ''.join(x) + ".jpg\")" + "\n"


file = open("abhi.txt", "w")
file.close()

with open("p1.txt", "r") as file:
    data = file.readlines();
    for line in data:
        word = line.split()
        file = open("abhi.txt", "a")        
        file.write(imageString(word))
        file.close()



file = open("main.py", "w")
file1 = open("header.txt", "r")
file.write(file1.read())
file.close()
file1.close()

file = open("main.py", "a")
file1 = open("abhi.txt", "r")
file.write(file1.read())
file.close()
file1.close()

file = open("main.py", "a")
file1 = open("p2.py", "r")
file.write(file1.read()) 
file.close()
file1.close()

exec(open("main.py").read())

