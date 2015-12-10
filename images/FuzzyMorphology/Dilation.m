function Dilation(namaFile)
t=imread(namaFile);
sq=ones(10,10);
td=imdilate(t,sq);
subplot(1,2,1), imshow(t)
subplot(1,2,2), imshow(td)