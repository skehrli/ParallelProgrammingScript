public interface Comm {
    
    public void Send(
        Object buf,        // Pntr to data to be sent
        int offset,
        int count,         // Number of items to be sent
        Datatype datatype, // Datatype of items
        int dest,          // Destination process id
        int tag            // Data id tag
    );

    public void Recv(
        Object buf,        // Pntr to buffer to receive to
        int offset,
        int count,         // Number of items to be received
        Datatype datatype, // Datatype of items
        int source,        // Source process id
        int tag            // Data id tag
    );

    public void Reduce(
        int sendoffset,
        Object recvbuf,
        int recvoffset,
        int count,
        Datatype datatype,
        Op op,
        int root
    );

    public void Bcast(
        int sendoffset,
        Object buf,
        int count,
        Datatype datatype,
        int root
    );

    public void Scatter(
        Object sendbuf,
        int sendoffset,
        int sendcount,
        Datatype sendtype,
        Object recvbuf,
        int recvoffset,
        int recvcount,
        Datatype recvtype,
        int root
    );

}


