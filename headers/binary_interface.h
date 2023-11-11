#ifndef interface_h
#define interface_h

#include <stdbool.h>
#include <stddef.h>

typedef void *ScalaResult;

ScalaResult scala_app_request(const char *data, const int data_len);

bool scala_app_result_ok(const ScalaResult result);

const char *scala_app_get_data(const ScalaResult result);
int scala_app_get_data_length(const ScalaResult result);

bool scala_app_free_result(ScalaResult result);

#ifndef SN_SKIP_INIT
int ScalaNativeInit(void);
#endif

#endif /* interface_h */
