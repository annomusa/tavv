function hasil = DKD(A,B)
temp = B;
[m,n] = size(B);

for i=1:m
    for j=1:n
        temp(i,j)=ConjunctionKD(B(i,j),A(i,j));
    end
end

hasil=max(max(temp));
end

