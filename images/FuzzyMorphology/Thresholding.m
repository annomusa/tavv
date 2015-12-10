%Dibuat oleh Bobby Alexander W
%Contoh penggunaan: Thresholding('model2-1.jpg',0.15,0.25);

function H=Thresholding(citra,bb,ba)
    %citra merupakan gambar sel yang ingin diperoses
    %bb merupakan batas bawah dari double thresholding
    %ba merupakan batas atas dari double thresholding
    
    A=imread(citra);
    A=rgb2hsv(A);A1=A(:,:,1);A2=A(:,:,2);A3=A(:,:,3);
    
    %proses segmentasi dengan double thresholding
    F4=A1>bb&A1<ba;
    %akhir dari proses
    
    str=ones(3);
    F5=imclose(imopen(F4,str),str); %noise removal
    F6=imdilate(F5,str)-imerode(F5,str); %edge detection
    F7=F6+A1; %object marking
    H=cat(3,F7,A2,A3);
    H=hsv2rgb(H);%mengembalikan citra ke rgb
    imshow(H); %tampilkan gambar
end

