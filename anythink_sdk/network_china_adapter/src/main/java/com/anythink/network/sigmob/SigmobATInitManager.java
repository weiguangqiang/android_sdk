package com.anythink.network.sigmob;

import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;

import com.androidquery.AQuery;
import com.anythink.core.api.ATInitMediation;
import com.anythink.core.common.base.AnyThinkBaseAdapter;
import com.sigmob.windad.WindAdError;
import com.sigmob.windad.WindAdOptions;
import com.sigmob.windad.WindAds;
import com.sigmob.windad.fullscreenvideo.WindFullScreenAdRequest;
import com.sigmob.windad.fullscreenvideo.WindFullScreenVideoAd;
import com.sigmob.windad.fullscreenvideo.WindFullScreenVideoAdListener;
import com.sigmob.windad.rewardedVideo.WindRewardAdRequest;
import com.sigmob.windad.rewardedVideo.WindRewardInfo;
import com.sigmob.windad.rewardedVideo.WindRewardedVideoAd;
import com.sigmob.windad.rewardedVideo.WindRewardedVideoAdListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SigmobATInitManager extends ATInitMediation {

    public static final String TAG = SigmobATInitManager.class.getSimpleName();

    private String mAppId;
    private String mAppKey;

    private ConcurrentHashMap<String, AnyThinkBaseAdapter> mAdapterMap;
    private ConcurrentHashMap<String, AnyThinkBaseAdapter> mLoadResultAdapterMap;
    private ConcurrentHashMap<String, Object> adObject = new ConcurrentHashMap<>();

    private WindRewardedVideoAdListener windRewardedVideoAdListener = new WindRewardedVideoAdListener() {

        @Override
        public void onVideoAdLoadSuccess(String placementId) {
            AnyThinkBaseAdapter baseAdapter = mLoadResultAdapterMap.get(placementId);
            try {
                if (baseAdapter instanceof SigmobATRewardedVideoAdapter) {
                    ((SigmobATRewardedVideoAdapter) baseAdapter).onVideoAdLoadSuccess(placementId);
                }
            } catch (Throwable e) {

            }

            try {
                if (baseAdapter instanceof SigmobATInterstitialAdapter) {
                    ((SigmobATInterstitialAdapter) baseAdapter).onFullScreenVideoAdLoadSuccess(placementId);
                }
            } catch (Throwable e) {
            }

            removeLoadResultAdapter(placementId);

        }

        @Override
        public void onVideoAdPreLoadSuccess(String placementId) {
            AnyThinkBaseAdapter baseAdapter = mAdapterMap.get(placementId);
            try {
                if (baseAdapter instanceof SigmobATRewardedVideoAdapter) {
                    ((SigmobATRewardedVideoAdapter) baseAdapter).onVideoAdPreLoadSuccess(placementId);
                }
            } catch (Throwable e) {

            }
        }

        @Override
        public void onVideoAdPreLoadFail(String placementId) {
            AnyThinkBaseAdapter baseAdapter = mAdapterMap.get(placementId);
            try {
                if (baseAdapter instanceof SigmobATRewardedVideoAdapter) {
                    ((SigmobATRewardedVideoAdapter) baseAdapter).onVideoAdPreLoadFail(placementId);
                }
            } catch (Throwable e) {

            }

            try {
                if (baseAdapter instanceof SigmobATInterstitialAdapter) {
                    ((SigmobATInterstitialAdapter) baseAdapter).onFullScreenVideoAdPreLoadFail(placementId);
                }
            } catch (Throwable e) {

            }

        }

        @Override
        public void onVideoAdPlayStart(String placementId) {
            AnyThinkBaseAdapter baseAdapter = mAdapterMap.get(placementId);
            try {
                if (baseAdapter instanceof SigmobATRewardedVideoAdapter) {
                    ((SigmobATRewardedVideoAdapter) baseAdapter).onVideoAdPlayStart(placementId);
                }
            } catch (Throwable e) {

            }

            try {
                if (baseAdapter instanceof SigmobATInterstitialAdapter) {
                    ((SigmobATInterstitialAdapter) baseAdapter).onFullScreenVideoAdPlayStart(placementId);
                }
            } catch (Throwable e) {

            }

        }

        @Override
        public void onVideoAdPlayEnd(String s) {
            AnyThinkBaseAdapter baseAdapter = mAdapterMap.get(s);
            try {
                if (baseAdapter instanceof SigmobATRewardedVideoAdapter) {
                    ((SigmobATRewardedVideoAdapter) baseAdapter).onVideoAdPlayEnd(s);
                }
            } catch (Throwable e) {

            }

            try {
                if (baseAdapter instanceof SigmobATInterstitialAdapter) {
                    ((SigmobATInterstitialAdapter) baseAdapter).onFullScreenVideoAdPlayEnd(s);
                }
            } catch (Throwable e) {

            }

        }

        @Override
        public void onVideoAdClicked(String placementId) {
            AnyThinkBaseAdapter baseAdapter = mAdapterMap.get(placementId);
            try {
                if (baseAdapter instanceof SigmobATRewardedVideoAdapter) {
                    ((SigmobATRewardedVideoAdapter) baseAdapter).onVideoAdClicked(placementId);
                }
            } catch (Throwable e) {

            }

            try {
                if (baseAdapter instanceof SigmobATInterstitialAdapter) {
                    ((SigmobATInterstitialAdapter) baseAdapter).onFullScreenVideoAdClicked(placementId);
                }
            } catch (Throwable e) {

            }

        }

        //The isComplete method in WindRewardInfo returns whether it is completely played
        @Override
        public void onVideoAdClosed(WindRewardInfo windRewardInfo, String placementId) {
            AnyThinkBaseAdapter baseAdapter = mAdapterMap.get(placementId);
            try {
                if (baseAdapter instanceof SigmobATRewardedVideoAdapter) {
                    ((SigmobATRewardedVideoAdapter) baseAdapter).onVideoAdClosed(windRewardInfo, placementId);
                }
            } catch (Throwable e) {

            }

            try {
                if (baseAdapter instanceof SigmobATInterstitialAdapter) {
                    ((SigmobATInterstitialAdapter) baseAdapter).onFullScreenVideoAdClosed(placementId);
                }
            } catch (Throwable e) {

            }

            removeAdapter(placementId);
        }

        /**
         * Load ad error callback
         */
        @Override
        public void onVideoAdLoadError(WindAdError windAdError, String placementId) {
            AnyThinkBaseAdapter baseAdapter = mLoadResultAdapterMap.get(placementId);
            try {
                if (baseAdapter instanceof SigmobATRewardedVideoAdapter) {
                    ((SigmobATRewardedVideoAdapter) baseAdapter).onVideoAdLoadError(windAdError, placementId);
                }

            } catch (Throwable e) {

            }

            try {
                if (baseAdapter instanceof SigmobATInterstitialAdapter) {
                    ((SigmobATInterstitialAdapter) baseAdapter).onFullScreenVideoAdLoadError(windAdError, placementId);
                }
            } catch (Throwable e) {

            }

            removeLoadResultAdapter(placementId);

        }


        /**
         * Playback error
         */
        @Override
        public void onVideoAdPlayError(WindAdError windAdError, String placementId) {
            AnyThinkBaseAdapter baseAdapter = mAdapterMap.get(placementId);
            try {
                if (baseAdapter instanceof SigmobATRewardedVideoAdapter) {
                    ((SigmobATRewardedVideoAdapter) baseAdapter).onVideoAdPlayError(windAdError, placementId);
                }
            } catch (Throwable e) {

            }

            try {
                if (baseAdapter instanceof SigmobATInterstitialAdapter) {
                    ((SigmobATInterstitialAdapter) baseAdapter).onFullScreenVideoAdPlayError(windAdError, placementId);
                }
            } catch (Throwable e) {

            }

            removeAdapter(placementId);
        }
    };

    private WindFullScreenVideoAdListener windFullScreenVideoAdListener = new WindFullScreenVideoAdListener() {

        @Override
        public void onFullScreenVideoAdLoadSuccess(String placementId) {
            AnyThinkBaseAdapter baseAdapter = mLoadResultAdapterMap.get(placementId);
            try {
                if (baseAdapter instanceof SigmobATInterstitialAdapter) {
                    ((SigmobATInterstitialAdapter) baseAdapter).onFullScreenVideoAdLoadSuccess(placementId);
                }
            } catch (Throwable e) {

            }
        }

        @Override
        public void onFullScreenVideoAdPreLoadSuccess(String placementId) {
            AnyThinkBaseAdapter baseAdapter = mAdapterMap.get(placementId);
            try {
                if (baseAdapter instanceof SigmobATInterstitialAdapter) {
                    ((SigmobATInterstitialAdapter) baseAdapter).onFullScreenVideoAdPreLoadSuccess(placementId);
                }
            } catch (Throwable e) {

            }
        }

        @Override
        public void onFullScreenVideoAdPreLoadFail(String placementId) {
            AnyThinkBaseAdapter baseAdapter = mAdapterMap.get(placementId);
            try {
                if (baseAdapter instanceof SigmobATInterstitialAdapter) {
                    ((SigmobATInterstitialAdapter) baseAdapter).onFullScreenVideoAdPreLoadFail(placementId);
                }
            } catch (Throwable e) {

            }
        }

        @Override
        public void onFullScreenVideoAdPlayStart(String placementId) {
            AnyThinkBaseAdapter baseAdapter = mAdapterMap.get(placementId);
            try {
                if (baseAdapter instanceof SigmobATInterstitialAdapter) {
                    ((SigmobATInterstitialAdapter) baseAdapter).onFullScreenVideoAdPlayStart(placementId);
                }
            } catch (Throwable e) {

            }
        }

        @Override
        public void onFullScreenVideoAdPlayEnd(String placementId) {
            AnyThinkBaseAdapter baseAdapter = mAdapterMap.get(placementId);
            try {
                if (baseAdapter instanceof SigmobATInterstitialAdapter) {
                    ((SigmobATInterstitialAdapter) baseAdapter).onFullScreenVideoAdPlayEnd(placementId);
                }
            } catch (Throwable e) {

            }
        }

        @Override
        public void onFullScreenVideoAdClicked(String placementId) {
            AnyThinkBaseAdapter baseAdapter = mAdapterMap.get(placementId);
            try {
                if (baseAdapter instanceof SigmobATInterstitialAdapter) {
                    ((SigmobATInterstitialAdapter) baseAdapter).onFullScreenVideoAdClicked(placementId);
                }
            } catch (Throwable e) {

            }
        }

        @Override
        public void onFullScreenVideoAdClosed(String placementId) {
            AnyThinkBaseAdapter baseAdapter = mAdapterMap.get(placementId);
            try {
                if (baseAdapter instanceof SigmobATInterstitialAdapter) {
                    ((SigmobATInterstitialAdapter) baseAdapter).onFullScreenVideoAdClosed(placementId);
                }
            } catch (Throwable e) {

            }
            removeAdapter(placementId);
        }

        @Override
        public void onFullScreenVideoAdLoadError(WindAdError windAdError, String placementId) {
            AnyThinkBaseAdapter baseAdapter = mLoadResultAdapterMap.get(placementId);
            try {
                if (baseAdapter instanceof SigmobATInterstitialAdapter) {
                    ((SigmobATInterstitialAdapter) baseAdapter).onFullScreenVideoAdLoadError(windAdError, placementId);
                }
            } catch (Throwable e) {

            }
            removeAdapter(placementId);
        }

        @Override
        public void onFullScreenVideoAdPlayError(WindAdError windAdError, String placementId) {
            AnyThinkBaseAdapter baseAdapter = mAdapterMap.get(placementId);
            try {
                if (baseAdapter instanceof SigmobATInterstitialAdapter) {
                    ((SigmobATInterstitialAdapter) baseAdapter).onFullScreenVideoAdPlayError(windAdError, placementId);
                }
            } catch (Throwable e) {

            }
            removeAdapter(placementId);
        }
    };

    private SigmobATInitManager() {
        mAdapterMap = new ConcurrentHashMap<>();
        mLoadResultAdapterMap = new ConcurrentHashMap<>();
    }

    public synchronized static SigmobATInitManager getInstance() {
        return Holder.sSigmobInitManager;
    }

    @Override
    public synchronized void initSDK(Context context, Map<String, Object> serviceExtras) {
        initSDK(context, serviceExtras, null);
    }

    public static class Holder {
        static final SigmobATInitManager sSigmobInitManager = new SigmobATInitManager();
    }

    public synchronized void initSDK(Context context, Map<String, Object> serviceExtras, final InitCallback initCallback) {

        String app_id = (String) serviceExtras.get("app_id");
        String app_key = (String) serviceExtras.get("app_key");

        if (TextUtils.isEmpty(app_id) || TextUtils.isEmpty(app_key)) {
            if (initCallback != null) {
                initCallback.onFinish();
            }
            return;
        }
        if (TextUtils.isEmpty(mAppId) || TextUtils.isEmpty(mAppKey) || !TextUtils.equals(mAppId, app_id) || !TextUtils.equals(mAppKey, app_key)) {
            //init
            WindAds ads = WindAds.sharedAds();
            //enable or disable debug log ouput
//            ads.setDebugEnable(ATSDK.isNetworkLogDebug());

            // start SDK Init with Options
            if (ads.startWithOptions(context.getApplicationContext(), new WindAdOptions(app_id, app_key))) {
                mAppId = app_id;
                mAppKey = app_key;
            }
        }

        WindRewardedVideoAd.sharedInstance().setWindRewardedVideoAdListener(windRewardedVideoAdListener);
        WindFullScreenVideoAd.sharedInstance().setWindFullScreenVideoAdListener(windFullScreenVideoAdListener);

        if (initCallback != null) {
            initCallback.onFinish();
        }
    }

    public void loadInterstitial(final String placementid, WindFullScreenAdRequest request, SigmobATInterstitialAdapter arpuInterstitialAdapter) {
        putLoadResultAdapter(placementid, arpuInterstitialAdapter);
        WindFullScreenVideoAd.sharedInstance().loadAd(request);
    }

    public void loadRewardedVideo(final String placementid, WindRewardAdRequest request, AnyThinkBaseAdapter arpuRewardedVideoAdapter) {
        putLoadResultAdapter(placementid, arpuRewardedVideoAdapter);
        WindRewardedVideoAd.sharedInstance().loadAd(request);
    }


    protected synchronized void putAdapter(String instanceId, AnyThinkBaseAdapter baseAdapter) {
        mAdapterMap.put(instanceId, baseAdapter);
    }

    private synchronized void removeAdapter(String instanceId) {
        mAdapterMap.remove(instanceId);
    }

    protected synchronized void putLoadResultAdapter(String instanceId, AnyThinkBaseAdapter baseAdapter) {
        mLoadResultAdapterMap.put(instanceId, baseAdapter);
    }

    private synchronized void removeLoadResultAdapter(String instanceId) {
        mLoadResultAdapterMap.remove(instanceId);
    }

    protected void put(String adsourceId, Object object) {
        adObject.put(adsourceId, object);
    }

    protected void remove(String adsourceId) {
        adObject.remove(adsourceId);
    }


    interface InitCallback {
        void onFinish();
    }

    @Override
    public String getNetworkName() {
        return "Sigmob";
    }

    @Override
    public Map<String, Boolean> getPluginClassStatus() {
        HashMap<String, Boolean> pluginMap = new HashMap<>();

        pluginMap.put("implementation 'com.android.support:localbroadcastmanager:+'", false);

        Class clazz;
        try {
            clazz = LocalBroadcastManager.class;
            pluginMap.put("implementation 'com.android.support:localbroadcastmanager:+'", true);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return pluginMap;
    }
    @Override
    public String getNetworkSDKClass() {
        return "com.sigmob.windad.WindAds";
    }


    @Override
    public List getActivityStatus() {
        ArrayList<String> list = new ArrayList<>();
        list.add("com.sigmob.sdk.base.common.AdActivity");
        return list;
    }

    @Override
    public List getProviderStatus() {
        ArrayList<String> list = new ArrayList<>();
        list.add("com.sigmob.sdk.SigmobFileProvider");
        return list;
    }
}