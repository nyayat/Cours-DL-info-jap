#pragma once
/**
 * \enum PreprocessMode
 * \brief Décrit les différent algortihmes de preprocessing utilisables
 */
namespace Enumerations {
	typedef enum {
					ESTIMATE_1D_H, /**<One dimentional first order horizontal predictor \f$pre(x(i,j))=x(i-1,j)\f$*/
				}	PreprocessMode;
}

using namespace Enumerations;
