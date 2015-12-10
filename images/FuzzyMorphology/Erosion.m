function Erosion(namaFile)
t=imread(namaFile);
sq=ones(20,20);
td=imerode(t,sq);
subplot(1,2,1), imshow(t)
subplot(1,2,2), imshow(td)