package com.sagihatzabi.breakingline;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.sagihatzabi.breakingline.items.SagiVectorIcon;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CartFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment implements FoodAdapter.OnItemClickListener {


    private static final int NUM_OF_ITEMS = 3;
    private OnFragmentInteractionListener mListener;

    private LinearLayout mCartLinearLayout;
    private BurgersRecyclerView mRecyclerView;
    private ArrayList<SagiVectorIcon> mCart;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance() {
        CartFragment fragment = new CartFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        mCartLinearLayout = (LinearLayout) view.findViewById(R.id.fragment_cart_items);

        mCart = new ArrayList<>();
        mRecyclerView = createSnappingRecyclerView();
        mCartLinearLayout.addView(mRecyclerView);

        // Inflate the layout for this fragment
        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(SagiVectorIcon vectorIcon) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    BurgersRecyclerView createSnappingRecyclerView() {

        int width = mCartLinearLayout.getWidth();

        // specify an adapter (see also next example)
        FoodAdapter mAdapter = new FoodAdapter(mCart, this);

        // Create FoodRecyclerView
        BurgersRecyclerView mRecyclerView =
                BurgersRecyclerView.create(getActivity())
                        .addAdapter(mAdapter)
                        .setNumberOfItems(NUM_OF_ITEMS)
                        .setOrientation(LinearLayoutManager.HORIZONTAL)
                        .build();

        return mRecyclerView;
    }

    public void addItemToCart(SagiVectorIcon item) {
        this.mCart.add(item);
        mRecyclerView.mAdapter.notifyDataSetChanged();
    }
}
