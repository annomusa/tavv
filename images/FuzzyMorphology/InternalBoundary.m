function InternalBoundary(namaFile)
t=imread(namaFile);
sq=ones(10,10);
r = t>0;
re = imerode(r,sq);
r_int = r&~re;
subplot(1,2,1), imshow(r)
subplot(1,2,2), imshow(r_int)