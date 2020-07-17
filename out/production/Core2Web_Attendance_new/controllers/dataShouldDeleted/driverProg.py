def imageString( x ):
    # ''.join(x) + "_image = face_recognition.load_image_file(\"" + ''.join(x) + ".jpg\")"  + "\n" 
    str = "\\"
    return ''.join(x) + "_image = face_recognition.load_image_file(\"C:" + str + "\\Users" + str + "\\Abhi" + str + "\\Desktop" + str + "\\examples" + str + "\\Faces\\" + str + ''.join(x) + ".jpg\")"  + "\n" 
    

  # abhishek_image = face_recognition.load_image_file("C:\Users\Abhi\Desktop\examples\Faces\abhishek.jpg") old


   #abhishek_image = face_recognition.load_image_file("C:\\Users\\Abhi\\Desktop\\dataSet\\abhishek.jpg")   new req


file = open("Strings.txt", "w")
file.close()

with open("names.txt", "r") as file:
    data = file.readlines()
    for line in data:
        word = line.split()
        file = open("Strings.txt", "a")
        file.write(imageString(word))
        file.close()

file = open("mainProgram.py", "w")
file1= open("headerFiles.txt", "r")
file.write(file1.read())
file.close()
file1.close()

file = open("mainProgram.py", "a")
file1 = open("Strings.txt", "r")
file.write(file1.read())
file.close()
file1.close()

file = open("mainProgram.py", "a")
file1 = open("recognitionCode.py", "r")
file.write(file1.read())
file.close()
file1.close()

exec(open("mainProgram.py").read())