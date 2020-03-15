all: build simulate generateImages generateVideo preview

build:
	javac *.java

simulate:
	java Scenario

generateImages:
	java ImageGenerator out.txt

generateVideo:
	ffmpeg -r 30 -i genImage/%03d.png -c:v libx264 -vf fps=25 -pix_fmt yuv420p out.mp4

preview:
	xdg-open out.mp4