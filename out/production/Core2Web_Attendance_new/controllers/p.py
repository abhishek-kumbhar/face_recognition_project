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


file = open("abhi.txt", "a")
file.write("\n\n")
file.close()



