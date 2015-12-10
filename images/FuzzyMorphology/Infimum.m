function hasil = Infimum(A,B)
[m,n] = size(A);
for i=1:m
    for j=1:n
        hasil(i,j)=min(A(i,j),B(i,j));
    end
end

end