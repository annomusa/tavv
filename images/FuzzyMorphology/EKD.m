function hasil = EKD(A,B)
temp = B;
[m,n] = size(B);

for i=1:m
    for j=1:n
        temp(i,j)=ImplicationKD(B(i,j),A(i,j));
    end
end

hasil=min(min(temp));
end

