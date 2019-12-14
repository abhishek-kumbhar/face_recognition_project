import cv2
import sys

print("in py")

# 1.creating a video object
video = cv2.VideoCapture(0)
# 2. Variable
a = 0
face_id = sys.argv[0]

# 3. While loop
while a<10:
    a = a + 1
    # 4.Create a frame object
    check, frame = video.read()
    # Converting to grayscale
    #gray = cv2.cvtColor(frame,cv2.COLOR_BGR2GRAY)
    # 5.show the frame!
    cv2.imshow("Capturing",frame)
    # 6.for playing
    key = cv2.waitKey(1)
    if key == ord('q'):
        break
# 7. image saving
showPic = cv2.imwrite("/users/omkarajagunde/development/dataSet/User." + str(face_id) + '.' + str(a) + ".jpeg",frame)
print(showPic)
# 8. shutdown the camera
video.release()
cv2.destroyAllWindows()