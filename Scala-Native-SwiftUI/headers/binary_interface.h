#ifndef interface_h
#define interface_h

typedef struct ByteArray {
    const int size;
    const char* bytes;
} ByteArray;

void scala_app_request(const ByteArray* message, ByteArray* response_holder);
void scala_app_notification(const ByteArray* message);



#ifndef SN_SKIP_INIT
int ScalaNativeInit(void);
#endif

#endif /* interface_h */
