function Deploy
t=imread('080112a.tif');
sq=ones(3,3);
td=imdilate(t,sq);
subplot(1,2,1), imshow(t)
subplot(1,2,2), imshow(td)