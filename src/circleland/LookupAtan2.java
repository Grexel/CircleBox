/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

/**
 *
 * @author Jeff
 */
public class LookupAtan2{
    //Taken from http://www.java-gaming.org/index.php?topic=14647.0
    //Author Sproingie
    private static final int           SIZE                 = 1024;
    private static final float         STRETCH            = (float)Math.PI;
    // Output will swing from -STRETCH to STRETCH (default: Math.PI)
    // Useful to change to 1 if you would normally do "atan2(y, x) / Math.PI"

    // Inverse of SIZE
    private static final int        EZIS            = -SIZE;
    private static final float[]    ATAN2_TABLE_PPY    = new float[SIZE + 1];
    private static final float[]    ATAN2_TABLE_PPX    = new float[SIZE + 1];
    private static final float[]    ATAN2_TABLE_PNY    = new float[SIZE + 1];
    private static final float[]    ATAN2_TABLE_PNX    = new float[SIZE + 1];
    private static final float[]    ATAN2_TABLE_NPY    = new float[SIZE + 1];
    private static final float[]    ATAN2_TABLE_NPX    = new float[SIZE + 1];
    private static final float[]    ATAN2_TABLE_NNY    = new float[SIZE + 1];
    private static final float[]    ATAN2_TABLE_NNX    = new float[SIZE + 1];

    public static void initialize()
    {
        for (int i = 0; i <= SIZE; i++)
        {
            float f = (float)i / SIZE;
            ATAN2_TABLE_PPY[i] = (float)(StrictMath.atan(f) * STRETCH / StrictMath.PI);
            ATAN2_TABLE_PPX[i] = STRETCH * 0.5f - ATAN2_TABLE_PPY[i];
            ATAN2_TABLE_PNY[i] = -ATAN2_TABLE_PPY[i];
            ATAN2_TABLE_PNX[i] = ATAN2_TABLE_PPY[i] - STRETCH * 0.5f;
            ATAN2_TABLE_NPY[i] = STRETCH - ATAN2_TABLE_PPY[i];
            ATAN2_TABLE_NPX[i] = ATAN2_TABLE_PPY[i] + STRETCH * 0.5f;
            ATAN2_TABLE_NNY[i] = ATAN2_TABLE_PPY[i] - STRETCH;
            ATAN2_TABLE_NNX[i] = -STRETCH * 0.5f - ATAN2_TABLE_PPY[i];
        }
    }

    /**
     * ATAN2
     */

    public static final float atan2(float y, float x)
    {
        if (x >= 0)
        {
            if (y >= 0)
            {
                if (x >= y)
                    return ATAN2_TABLE_PPY[(int)(SIZE * y / x + 0.5)];
                else
                    return ATAN2_TABLE_PPX[(int)(SIZE * x / y + 0.5)];
            }
            else
            {
                if (x >= -y)
                    return ATAN2_TABLE_PNY[(int)(EZIS * y / x + 0.5)];
                else
                    return ATAN2_TABLE_PNX[(int)(EZIS * x / y + 0.5)];
            }
        }
        else
        {
            if (y >= 0)
            {
                if (-x >= y)
                    return ATAN2_TABLE_NPY[(int)(EZIS * y / x + 0.5)];
                else
                    return ATAN2_TABLE_NPX[(int)(EZIS * x / y + 0.5)];
            }
            else
            {
                if (x <= y) // (-x >= -y)
                    return ATAN2_TABLE_NNY[(int)(SIZE * y / x + 0.5)];
                else
                    return ATAN2_TABLE_NNX[(int)(SIZE * x / y + 0.5)];
            }
        }
    }
}
